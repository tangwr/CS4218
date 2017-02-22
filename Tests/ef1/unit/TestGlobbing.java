package ef1.unit;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

public class TestGlobbing {

	static ShellImpl shellImpl;
	static ByteArrayOutputStream output;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		shellImpl = new ShellImpl();
	}
	
	@Test
	public void catGlobNoPath(){
		String input = "cat asds*d.txt";
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		String expected = "", actual;
		
		try {
			shellImpl.parseAndEvaluate(input, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			fail();
		} catch (ShellException e) {
			// TODO Auto-generated catch block
			fail();
		}
		actual = output.toString();
		assertEquals(expected, actual);		
	}

	@Test
	public void catGlobOneFile(){
		String input = "cat Tests\\globFiles\\glob*Source1.txt";
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		String expected = "hello world\n", actual;
		
		try {
			shellImpl.parseAndEvaluate(input, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			fail();
		} catch (ShellException e) {
			// TODO Auto-generated catch block
			fail();
		}
		actual = output.toString();
		assertEquals(expected, actual);		
	}
	
	@Test
	public void catGlobDirectories(){
		String input = "cat Tests\\g*b*\\globTestSource1.txt";
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		String expected = "hello world\n", actual;
		
		try {
			shellImpl.parseAndEvaluate(input, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			fail();
		} catch (ShellException e) {
			// TODO Auto-generated catch block
			fail();
		}
		actual = output.toString();
		assertEquals(expected, actual);		
	}
	
	@Test
	public void catGlobFilesDirectories(){
		String input = "cat Tests\\g*b*\\glob*Sou*e1.txt";
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		String expected = "hello world\n", actual;
		
		try {
			shellImpl.parseAndEvaluate(input, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			fail();
		} catch (ShellException e) {
			// TODO Auto-generated catch block
			fail();
		}
		actual = output.toString();
		assertEquals(expected, actual);		
	}
	
	@Test
	public void catGlobMultipleFilesDirectories(){
		String input = "cat Tes*s\\g*b*\\glob*Sou*e1.txt Tes*s\\g*b*\\glob*e2.txt";
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		String expected = "hello world\nhello world 2\n", actual;
		
		try {
			shellImpl.parseAndEvaluate(input, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			fail();
		} catch (ShellException e) {
			// TODO Auto-generated catch block
			fail();
		}
		actual = output.toString();
		assertEquals(expected, actual);		
	}
	

	@Test
	public void catGlobMultipleFile(){
		String input = "cat Tests\\globFiles\\glob*.txt";
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		String expected = "hello world\nhello world 2\n", actual;
		
		try {
			shellImpl.parseAndEvaluate(input, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			fail();
		} catch (ShellException e) {
			// TODO Auto-generated catch block
			fail();
		}
		actual = output.toString();
		assertEquals(expected, actual);		
	}

}
