package backend.montyhall;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class MontyHallControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MontyHallGame montyHallGame;

    @Test
    @DisplayName("Should return number of wins")
    public void shouldReturnWins() throws Exception {
        when(montyHallGame.wins(anyInt(), anyBoolean())).thenReturn(100);

        String response = mockMvc.perform(get("/wins?numberOfSimulations=100&switchDoors=false"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(montyHallGame, times(1)).wins(anyInt(), anyBoolean());
        assertEquals("100", response);
    }
}