package net.engineeringdigest.journalApp.Service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

     @Transactional
     public void saveEntry(JournalEntry journalEntry, String userName){

         try{
             User user = userService.findByUserName(userName);
             journalEntry.setDate(LocalDateTime.now());
             JournalEntry saved = journalEntryRepository.save(journalEntry);
             user.getJournalEntries().add(saved);
             userService.saveUser(user);

         } catch (Exception e){
             log.error("exception", e);
             throw new RuntimeException("An error occurred while saving the entry", e);
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

     public boolean deleteById(ObjectId Id, String userName){
         boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed  = user.getJournalEntries().removeIf(x -> x.getId().equals(Id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(Id);
            }

        } catch (Exception e){
         System.out.println(e);
         throw new RuntimeException("An error occured while saving the entry", e);
        }
        return removed;

//     public List<JournalEntry> findByUserName(String userName){
//         User user = userService.findByUserName(userName);
//         return user.getJournalEntries() ;
//
     }
}
