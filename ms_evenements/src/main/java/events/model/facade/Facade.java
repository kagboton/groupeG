package events.model.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import events.model.domaine.Codebeautify;
import events.model.domaine.EventPrivate;
import events.model.domaine.EventPublic;
import events.model.domaine.Fields;
import events.model.exceptions.EventNotFoundException;
import events.repository.EventPrivateRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Facade implements IEventPrivate, IEventPublic {

    @Autowired
    private EventPrivateRepository eventPrivateRepository;


    public static EventPublic extraire(Codebeautify codebeautify) {
        Fields event = codebeautify.getFields();
        String idEvent = codebeautify.getRecordid();
        String title = event.getTitle();
        String placename = event.getPlacename();
        String pricing_info = event.getPricing_info();
        String imageURL = event.getImage();
        LocalDate date_start = LocalDate.parse(event.getDate_start());
        String department = event.getDepartment();
        String city = event.getCity();
        String link = event.getLink();
        String free_text = event.getFree_text();
        String address = event.getAddress();
        String region = event.getRegion();
        LocalDate date_end = LocalDate.parse(event.getDate_end());
        String tags = event.getTags();
        String description = event.getDescription();
        String space_time_info = event.getSpace_time_info();

        EventPublic eventPublic = new EventPublic(idEvent, title, placename, pricing_info, imageURL, date_start, space_time_info,
                department, city, link, free_text, address, region, date_end, tags, description);
        return eventPublic;
    }

    @Override
    public Iterable<EventPrivate> getAllEventsByPseudo(String pseudo) throws EventNotFoundException {
        return Optional.ofNullable(eventPrivateRepository.findEventPrivatesByPseudo(pseudo)).orElseThrow(()-> new EventNotFoundException("Event list not found"));
    }

    @Override
    public Optional<EventPrivate> getEvent(Long idEvent) {
        return Optional.ofNullable(eventPrivateRepository.findById(idEvent)).orElseThrow(() -> new EventNotFoundException("Event not found"));
    }

    @Override
    public void updateEvent(EventPrivate eventPrivate, Long idEvent) {
        EventPrivate eventPrivate1 = eventPrivateRepository.findById(idEvent).get();
        eventPrivate.setIdEvent(eventPrivate1.getIdEvent());
        eventPrivate.setPseudo(eventPrivate1.getPseudo());
        eventPrivateRepository.save(eventPrivate1);
    }

    @Override
    public void deleteEvent(Long idEvent) {
        eventPrivateRepository.deleteById(idEvent);
    }

    @Override
    public EventPrivate createEvent(EventPrivate eventPrivate) {
        return eventPrivateRepository.save(eventPrivate);
    }

    @Override
    public EventPublic getEventPublicById(String idEvent) throws EventNotFoundException {
        EventPublic eventPublic = null;
        Client client = ClientBuilder.newClient();
        WebTarget resource = client.target("https://data.orleans-metropole.fr/api/datasets/1.0/evenements-publics-openagenda/records/" + idEvent);
        Response response = requestBuilder(resource);
        String out;
        if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
            out = response.readEntity(String.class);
            Codebeautify targetObject = new Gson().fromJson(out, Codebeautify.class);
            eventPublic = extraire(targetObject);
        } else {
            throw new EventNotFoundException("L'évènement " + idEvent + " n'existe pas ");
        }
        return eventPublic;

    }

    @Override
    public List<EventPublic> getAllPublicEvents(int rows, int start) throws IOException, EventNotFoundException {
        String url = "https://data.orleans-metropole.fr/api/records/1.0/search/?dataset=evenements-publics-openagenda&lang=Fr&rows=" + rows + "&start=" + start + "&sort=date_start&facet=tags&facet=placename&facet=department&facet=region&facet=city&facet=date_start&facet=date_end&facet=pricing_info&facet=updated_at&facet=city_district";
        Client client = ClientBuilder.newClient();
        WebTarget resource = client.target(url);
        Response response = requestBuilder(resource);
        String output;
        List<EventPublic> eventPublicList = null;
        if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
            output = response.readEntity(String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JSONObject jsonObj = new JSONObject(output);
            JSONArray allRecords = jsonObj.getJSONArray("records");
            eventPublicList = new ArrayList<>();
            for (int i = 0; i < allRecords.length(); i++) {
                JSONObject json = allRecords.getJSONObject(i);
                //convert json string to object
                Codebeautify event = objectMapper.readValue(json.toString(), Codebeautify.class);
                eventPublicList.add(extraire(event));
            }
        } else {
            throw new EventNotFoundException("La liste des évènements n'a pas été trouvée ");
        }
        return eventPublicList;
    }

    //Méthode intermédiaire pour la récupération d'une ressource externe
    public Response requestBuilder(WebTarget resource) {
        Invocation.Builder request = resource.request();
        request.accept(MediaType.APPLICATION_JSON);
        Response response = request.get();
        return response;
    }


}
