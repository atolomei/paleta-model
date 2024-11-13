package io.paleta.service;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.paleta.logging.Logger;
import io.paleta.model.JsonObject;
import io.paleta.model.SharedConstant;

public class BaseService extends JsonObject {

		static private Logger logger = Logger.getLogger(BaseService.class.getName());
		
		//@JsonIgnore
		//static final private RandomIDGenerator idGenerator = new RandomIDGenerator();
		
		@JsonIgnore
		static final private ObjectMapper mapper = new ObjectMapper();
		
		static {
			mapper.registerModule(new JavaTimeModule());
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.registerModule(new Jdk8Module());
		}

		//static protected String randomString(final int size) {
		//	return idGenerator.randomString(size);
		//}
		
		@JsonIgnore
		private ServiceStatus status;
		
		public BaseService() {
			this.status=ServiceStatus.STOPPED;
		}
		
		public void setStatus(ServiceStatus status) {
			this.status=status;
		}
		
		public ServiceStatus getStatus() {
			return this.status;
		}

		@Override
		public String toString() {
			StringBuilder str = new StringBuilder();
			str.append(this.getClass().getSimpleName());
			str.append(toJSON());
			return str.toString();
		}

		@Override
		public String toJSON() {
		   try {
				return mapper.writeValueAsString(this);
			} catch (JsonProcessingException e) {
						logger.error(e, SharedConstant.NOT_THROWN);
						return "\"error\":\"" + e.getClass().getName()+ " | " + e.getMessage()+"\""; 
			}
		  }
	  
		
		public ObjectMapper getObjectMapper() {
			return mapper;
		}

}
