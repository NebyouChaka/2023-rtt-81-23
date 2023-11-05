package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import jpa.service.StudentService;

public class JunitTest {
	 @Test
	    public void ValidateStudents() {
	        StudentService validator = new StudentService();
	        
	        String email = "aiannitti7@is.gd";
	        String password = "TWP4hf5j";
	        
	        boolean actual = validator.validateStudent(email, password);
	        
	        
	        assertTrue(actual); 
	    }
	    
	    @Test
	    public void verfyingStudentValidation() {
	       	StudentService validator = new StudentService();
	       
	       	String email = "nebyouchaka@gmail.com";
	        String password = "FnVklVgC6r6";
	        
	        boolean actual = validator.validateStudent(email, password);
	        
	      
	        assertFalse(actual); 
	    }
	    
	    @Test
	    public void testemailnotValidStudent() {
	       	StudentService validator = new StudentService();
	       
	       	String email = "nebyouchaka@gmail.com";
	        String password = "X1uZcoIh0dj";
	        
	        boolean actual = validator.validateStudent(email, password);
	        
	        assertFalse(actual); 
	    }
	    @Test
	    public void testpasswordnotValidStudent() {
	       	StudentService validator = new StudentService();
	       	String email = "cstartin3@flickr.com";
	        String password = "password";
	        
	        boolean actual = validator.validateStudent(email, password);
	        assertFalse(actual); 
	    }
	    

	}