package net.engineeringdigest.journalApp.Service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

     @Autowired
     private UserRepository userRepository;


     public void saveEntry(User user){
         userRepository.save(user);

     }

     public List<User> getAll(){
         return userRepository.findAll();
     }

     public User findByUserName(String userName){
         return  userRepository.findByUserName(userName);
     }

     public void deleteById(ObjectId Id){
         userRepository.deleteById(Id);
     }
}
