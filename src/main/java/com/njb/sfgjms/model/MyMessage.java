package com.njb.sfgjms.model;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyMessage implements Serializable {

	static final long serialVersionUID = -6703826490277916847L;

	private UUID id;
	private String message;
}
