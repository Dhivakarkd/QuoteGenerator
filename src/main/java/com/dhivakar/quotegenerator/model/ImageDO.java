package com.dhivakar.quotegenerator.model;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quote_image_meta")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "filename")
    private String fileName;
    @Column(name = "filesize")
    private long fileSize;
    @UpdateTimestamp
    @Column(name = "createdtime")
    private LocalDateTime createTimeStamp;


}
