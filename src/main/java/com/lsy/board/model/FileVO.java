package com.lsy.board.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fno;

	private Long bno;
	private String savefolder;
	private String originfile;
	private String savefilename;
	
	@Lob
	private String savefileurl;
	
	private String filetype;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date regdate;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.REMOVE)
	@JoinColumn(name="board_id")
	private Board board;
	
	
}
