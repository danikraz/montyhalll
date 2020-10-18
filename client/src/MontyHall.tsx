import {
  Button,
  Checkbox,
  Box,
  FormControlLabel,
  TextField,
  CircularProgress,
  Container,
  Typography,
  makeStyles,
  Card,
  CardContent,
} from "@material-ui/core";
import axios from "axios";
import React, { FC, useState } from "react";
import { trackPromise, usePromiseTracker } from "react-promise-tracker";
import montyHall from "./montyhall.jpeg";
import { toast } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

const useStyles = makeStyles((theme) => ({
  spacing: {
    marginRight: theme.spacing(2),
  },
}));

const MontyHall: FC = () => {
  const classes = useStyles();
  const [numberOfSimulations, setNumberOfSimulations] = useState(0);
  const [switchDoors, setswitchDoors] = useState(true);
  const [simulationResult, setSimulationResult] = useState("");
  const { promiseInProgress } = usePromiseTracker();

  const playMontyHall = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    fetchResult();
  };

  const fetchResult = () => {
    trackPromise(
      axios
        .get<string>(
          `/wins?numberOfSimulations=${numberOfSimulations}&switchDoors=${switchDoors}`
        )
        .then((response) => setSimulationResult(response.data))
        .catch((error) =>
          toast.error(error.message, {
            position: "top-right",
            autoClose: 5000,
          })
        )
    );
  };

  return (
    <Container maxWidth="md">
      <Box
        display="flex"
        pt={3}
        justifyContent="center"
        flexDirection="column"
        alignItems="center"
      >
        <Typography variant="h3" component="h1">
          Monty Hall Demo
        </Typography>
        <img src={montyHall} alt="monty hall" />
      </Box>

      <form data-cy="monty-form" onSubmit={(e) => playMontyHall(e)}>
        <Box display="flex" justifyContent="center" mt={2}>
          <TextField
            data-cy="simulations-input"
            className={classes.spacing}
            label="Number of simulations"
            type="number"
            InputLabelProps={{
              shrink: true,
            }}
            InputProps={{ inputProps: { min: 1, max: 2147483647 } }}
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
        </Box>

        <Box display="flex" justifyContent="center" mt={3}>
          <Button
            type="submit"
            variant="contained"
            color="primary"
            disabled={numberOfSimulations < 1 || promiseInProgress}
          >
            Simulate
          </Button>
        </Box>

        <Box display="flex" justifyContent="center" mt={3}>
          {promiseInProgress && <CircularProgress />}

          {simulationResult && !promiseInProgress && (
            <Card data-cy="simulations-result">
                <CardContent>
                <Typography variant="body1">
                {`The player won ${simulationResult} times!`}
                </Typography>
                </CardContent>
            </Card>
          )}
        </Box>
      </form>
    </Container>
  );
};

export default MontyHall;
