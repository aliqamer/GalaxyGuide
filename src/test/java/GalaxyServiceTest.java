
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.galaxy.GalaxyMain;
import com.galaxy.service.GalaxyService;
import com.galaxy.service.GalaxyServiceImpl;
import com.galaxy.util.InputReader;
import com.galaxy.validator.InputValidator;

import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class GalaxyServiceTest {

	@Mock
	private InputReader inputReader;
	@Mock
	private InputValidator inputValidator;
	@Mock
	private GalaxyService galaxyService;
	@InjectMocks
	private GalaxyMain galaxyMain;
	
	@Before
	public void Setup(){

	}
	
	@Test
	public void testFindResult() {
		ArrayList<String> lines = newArrayList();
		when(inputReader.readInputFile(anyString(), anyString())).thenReturn(lines);
		when(galaxyService.getUnitResult(lines)).thenReturn(newArrayList());
		
		galaxyMain.findResult("path","filename");
		
		verify(inputValidator).validate(lines);
		verify(galaxyService).getUnitResult(lines);
	}
	
}
