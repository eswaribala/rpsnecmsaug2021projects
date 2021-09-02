package com.nec.customerapi.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.ForeignKey;

@Entity
@Table(name="Address")
@Data

public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(position = 1, required = true, hidden=true, notes = "Auto generated column")
	@Column(name="Address_Id")
    private long addressId;
	@Column(name="Door_No",nullable = false)
	private String doorNo;
	@Column(name="Street_Name",nullable = false)
	private String streetName;
	@Column(name="City",nullable = false)
	private String city;
	@Column(name="State",nullable = false)
	private String state;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "Customer_Id"), name = "CustomerFK_Id")
	@JsonIgnore
	private Customer customer;
}
