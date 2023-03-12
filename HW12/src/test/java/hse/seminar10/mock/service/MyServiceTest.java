package hse.seminar10.mock.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import hse.seminar10.mock.api.DataService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Tests for {@link MyService}.
 *
 * @author Igor Khokhlov
 */
@ExtendWith(MockitoExtension.class)
class MyServiceTest {

  @Mock private DataService dataService;

  @InjectMocks private MyService myService;

  @Test
  @DisplayName("Should call getData()")
  void findDataList() {
    // given
    var id = "someID";
    var idList = List.of("123", "456");

    // and
    when(dataService.getDataById(id)).thenReturn("someData");
    when(dataService.getData()).thenReturn(List.of("RESULT"));

    // when
    var result = myService.findDataList(id, idList);

    // then
    assertThat(result).isNotNull().hasSize(1).containsOnly("RESULT");

    verify(dataService, times(0)).saveData(idList);
  }

  @Test
  void saveData() {
    // given
    var idList = new ArrayList<>(List.of("123", "456"));

    // and
    doAnswer(invocationOnMock -> {
      List<String> argument = invocationOnMock.getArgument(0);
      argument.add("789");
      return null;
    }).when(dataService).saveData(idList);

    // when
    assertDoesNotThrow(() -> myService.saveData(idList));

    // then
    assertThat(idList).isNotNull().hasSize(3);

    verify(dataService, times(1)).saveData(idList);
  }
}