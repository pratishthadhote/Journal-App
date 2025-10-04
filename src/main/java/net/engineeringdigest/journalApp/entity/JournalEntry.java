package net.engineeringdigest.journalApp.entity;

import com.sun.org.apache.xpath.internal.objects.XString;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

@Document(collection = "Journal_Entry_byUser")
@Data
@NoArgsConstructor

public class JournalEntry {

   @Id
   private ObjectId id;

   private String title;

   private String content;

    private LocalDateTime date;


}
