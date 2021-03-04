/**
 * 
 */
package br.com.tecweb.model;

import lombok.Data;

/**
 * @author cbgomes
 *
 */
@Data
public class Client {
	
	private Long id;
	private String name;
	private String email;
	private String phone;
}
