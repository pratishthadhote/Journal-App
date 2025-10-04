package net.engineeringdigest.journalApp.Service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
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
public class JournalEntryService {

     @Autowired
     private JournalEntryRepository journalEntryRepository;

     @Autowired
     private UserService userService;


     public void saveEntry(JournalEntry journalEntry, String userName){

         try{
             User user = userService.findByUserName(userName);
             journalEntry.setDate(LocalDateTime.now());
             JournalEntry saved = journalEntryRepository.save(journalEntry);
             user.getJournalEntries().add(saved);
             userService.saveEntry(user);

         } catch (Exception e){
             log.error("exception", e);
         }
     }
    public void saveEntry(JournalEntry journalEntry){

        try{
            journalEntryRepository.save(journalEntry);
        }
        catch (Exception e){
            log.error("exception", e);
        }
    }

     public List<JournalEntry> getAll(){
         return journalEntryRepository.findAll();
     }

     public Optional<JournalEntry> findById(ObjectId Id){
         return  journalEntryRepository.findById(Id);
     }

     public void deleteById(ObjectId Id, String userName){
         User user = userService.findByUserName(userName);
         user.getJournalEntries().removeIf(x -> x.getId().equals(Id));
         userService.saveEntry(user);
         journalEntryRepository.deleteById(Id);
     }
}
