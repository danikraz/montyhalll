import {
  Button,
  Checkbox,
  Box,
  FormControlLabel,
  TextField,
} from "@material-ui/core";
import axios from "axios";
import React, { FC, useState } from "react";

const MontyHall: FC = () => {
  const [numberOfSimulations, setNumberOfSimulations] = useState(0);
  const [switchDoors, setswitchDoors] = useState(true);
  const [simulationResult, setSimulationResult] = useState("");

  const playMontyHall = (event: any) => {
    event.preventDefault();
    fetchResult();
  };

  const fetchResult = () => {
    axios
      .get<string>(
        `/wins?numberOfSimulations=${numberOfSimulations}&switchDoors=${switchDoors}`
      )
      .then((response) => setSimulationResult(response.data))
      .catch((error) => console.error(error));
  };

  return (
    <Box display="flex" justifyContent="center">
      <form onSubmit={(e) => playMontyHall(e)}>
        <TextField
          label="Number of simulations"
          type="number"
          InputLabelProps={{
            shrink: true,
          }}
          InputProps={{ inputProps: { min: 1 } }}
          variant="outlined"
          onChange={(e) =>
            setNumberOfSimulations(
              e.target.value ? parseInt(e.target.value) : 0
            )
          }
        />

        <FormControlLabel
          control={
            <Checkbox
              checked={switchDoors}
              onChange={() => setswitchDoors(!switchDoors)}
              color="primary"
            />
          }
          label="Switch doors"
        />

        <Button
          type="submit"
          variant="contained"
          color="primary"
          disabled={numberOfSimulations < 1}
        >
          Simulate
        </Button>

        <p>{simulationResult}</p>
      </form>
    </Box>
  );
};

export default MontyHall;
