package com.rob.FastQuestion.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@Data
public class QuestionFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Id файла")
    private Long id;

    @Version
    private Integer version;

    @ApiModelProperty("Название файла")
    @Column(name = "file_name")
    private String fileName;

    @ApiModelProperty("Название файла")
    @Column(name = "file_type")
    private String fileType;

    @ApiModelProperty("Рейтинг")
    @Column(name = "rate")
    private Long count = 0l;

    @Lob
    private byte[] data;

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonBackReference
    @ApiModelProperty("Вопрос")
    private Question question;
}
