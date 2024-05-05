package backend.data;

import backend.controller.PlaceController;
import backend.model.Role;
import backend.model.entity.Communication;
import backend.model.entity.Place;
import backend.model.entity.Supervision;
import backend.model.entity.User;
import backend.repository.CommunicationRepository;
import backend.repository.PlaceRepository;
import backend.repository.SupervisionRepository;
import backend.repository.UserRepository;
import backend.util.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@Component
public class Data {
    private final UserRepository userRepository;
    private final CommunicationRepository communicationRepository;
    private final SupervisionRepository supervisionRepository;
    private final PasswordEncoder passwordEncoder;
    private final PlaceRepository placeRepository;

    public Data(UserRepository userRepository, CommunicationRepository communicationRepository, SupervisionRepository supervisionRepository, PasswordEncoder passwordEncoder, PlaceRepository placeRepository) {
        this.userRepository = userRepository;
        this.communicationRepository = communicationRepository;
        this.supervisionRepository = supervisionRepository;
        this.passwordEncoder = passwordEncoder;
        this.placeRepository = placeRepository;
    }

    @PostConstruct
    private void initData() {
        log.info("INIT DATA");
        initUsers();
        initCommunications();
        initSupervisions();
        initPlaces();
    }

    private void initUsers() {
        List<User> users = userRepository.findAll();
        if(!CollectionUtils.isEmpty(users)) { return ; }
        log.info("INIT USERS");

        User user = new User();
        user.setEmail("adrien@gmail.com");
        user.setName("adrien");
        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode("adrien"));
        user.setActive(true);
        userRepository.save(user);

        user = new User();
        user.setEmail("user1@gmail.com");
        user.setName("user1");
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode("user1"));
        user.setActive(true);
        userRepository.save(user);

        user = new User();
        user.setEmail("user2@gmail.com");
        user.setName("user2");
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode("user2"));
        user.setActive(true);
        userRepository.save(user);

        user = new User();
        user.setEmail("user3@gmail.com");
        user.setName("user3");
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode("user3"));
        user.setActive(true);
        userRepository.save(user);

        user = new User();
        user.setEmail("user4@gmail.com");
        user.setName("user4");
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode("user4"));
        user.setActive(false);
        userRepository.save(user);

        user = new User();
        user.setEmail("user5@gmail.com");
        user.setName("user5");
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode("user5"));
        user.setActive(false);
        userRepository.save(user);
    }

    private void initCommunications() {
        List<Communication> communications = communicationRepository.findAll();
        if(!CollectionUtils.isEmpty(communications)) { return ; }
        log.info("INIT COMMUNICATIONS");
        List<User> users = userRepository.findByRole(Role.ADMIN);

        Random random = new Random();

        Communication communication = new Communication();
        communication.setTitle("Communication 1");
        communication.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam eget porta ante, interdum congue enim. Nulla quis lacus dignissim, ornare magna et, euismod felis. Sed dapibus erat ac purus interdum bibendum. Sed fermentum est in ex convallis laoreet. Maecenas maximus ultrices erat, non pellentesque elit vulputate non. In efficitur egestas sapien eu consectetur. Suspendisse potenti. Nulla tellus nisi, imperdiet eget augue eget, volutpat feugiat tortor. Maecenas sodales eu elit quis blandit. Mauris blandit turpis quam, in lacinia leo volutpat nec.");
        communication.setAuthor(users.get(random.nextInt(users.size())).getName());
        communication.setDate(new Date());
        communicationRepository.save(communication);

        communication = new Communication();
        communication.setTitle("Communication 2");
        communication.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam eget porta ante, interdum congue enim. Nulla quis lacus dignissim, ornare magna et, euismod felis. Sed dapibus erat ac purus interdum bibendum. Sed fermentum est in ex convallis laoreet. Maecenas maximus ultrices erat, non pellentesque elit vulputate non. In efficitur egestas sapien eu consectetur. Suspendisse potenti. Nulla tellus nisi, imperdiet eget augue eget, volutpat feugiat tortor. Maecenas sodales eu elit quis blandit. Mauris blandit turpis quam, in lacinia leo volutpat nec.");
        communication.setAuthor(users.get(random.nextInt(users.size())).getName());
        communication.setDate(new Date());
        communicationRepository.save(communication);

        communication = new Communication();
        communication.setTitle("Communication 3");
        communication.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam eget porta ante, interdum congue enim. Nulla quis lacus dignissim, ornare magna et, euismod felis. Sed dapibus erat ac purus interdum bibendum. Sed fermentum est in ex convallis laoreet. Maecenas maximus ultrices erat, non pellentesque elit vulputate non. In efficitur egestas sapien eu consectetur. Suspendisse potenti. Nulla tellus nisi, imperdiet eget augue eget, volutpat feugiat tortor. Maecenas sodales eu elit quis blandit. Mauris blandit turpis quam, in lacinia leo volutpat nec.");
        communication.setAuthor(users.get(random.nextInt(users.size())).getName());
        communication.setDate(new Date());
        communicationRepository.save(communication);

        communication = new Communication();
        communication.setTitle("Communication 4");
        communication.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam eget porta ante, interdum congue enim. Nulla quis lacus dignissim, ornare magna et, euismod felis. Sed dapibus erat ac purus interdum bibendum. Sed fermentum est in ex convallis laoreet. Maecenas maximus ultrices erat, non pellentesque elit vulputate non. In efficitur egestas sapien eu consectetur. Suspendisse potenti. Nulla tellus nisi, imperdiet eget augue eget, volutpat feugiat tortor. Maecenas sodales eu elit quis blandit. Mauris blandit turpis quam, in lacinia leo volutpat nec.");
        communication.setAuthor(users.get(random.nextInt(users.size())).getName());
        communication.setDate(new Date());
        communicationRepository.save(communication);

        communication = new Communication();
        communication.setTitle("Communication 5");
        communication.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam eget porta ante, interdum congue enim. Nulla quis lacus dignissim, ornare magna et, euismod felis. Sed dapibus erat ac purus interdum bibendum. Sed fermentum est in ex convallis laoreet. Maecenas maximus ultrices erat, non pellentesque elit vulputate non. In efficitur egestas sapien eu consectetur. Suspendisse potenti. Nulla tellus nisi, imperdiet eget augue eget, volutpat feugiat tortor. Maecenas sodales eu elit quis blandit. Mauris blandit turpis quam, in lacinia leo volutpat nec.");
        communication.setAuthor(users.get(random.nextInt(users.size())).getName());
        communication.setDate(new Date());
        communicationRepository.save(communication);

    }
    
    private void initSupervisions() {
        List<Supervision> supervisions = supervisionRepository.findAll();
        if(!CollectionUtils.isEmpty(supervisions)) { return;}

        Random random = new Random();

        List<User> admins = userRepository.findByRole(Role.ADMIN);
        List<User> users = userRepository.findByRole(Role.USER);
        
        Supervision supervision = new Supervision();
        supervision.setTitle("Supervision 1");
        supervision.setFormatter(admins.get(0).getName());
        supervision.setDate(new Date());
        
        Set<String> attendees = new HashSet<>();
        attendees.add(users.get(random.nextInt(users.size())).getId());
        attendees.add(users.get(random.nextInt(users.size())).getId());
        supervision.setAttendees(attendees);
        supervisionRepository.save(supervision);

        supervision = new Supervision();
        supervision.setTitle("Supervision 2");
        supervision.setFormatter(admins.get(0).getName());
        supervision.setDate(new Date());

        attendees = new HashSet<>();
        attendees.add(users.get(random.nextInt(users.size())).getId());
        attendees.add(users.get(random.nextInt(users.size())).getId());
        supervision.setAttendees(attendees);
        supervisionRepository.save(supervision);

        supervision = new Supervision();
        supervision.setTitle("Supervision 3");
        supervision.setFormatter(admins.get(0).getName());
        supervision.setDate(new Date());

        attendees = new HashSet<>();
        attendees.add(users.get(random.nextInt(users.size())).getId());
        attendees.add(users.get(random.nextInt(users.size())).getId());
        supervision.setAttendees(attendees);
        supervisionRepository.save(supervision);
    }

    public void initPlaces() {
        List<Place> places = placeRepository.findAll();
        if(!CollectionUtils.isEmpty(places)){ return;}

        log.info("INIT PLACES");

        Place place1 = new Place();
        place1.setPlace("Grand bureau");
        placeRepository.save(place1);

        Place place2 = new Place();
        place2.setPlace("Petit bureau");
        placeRepository.save(place2);
    }

}
