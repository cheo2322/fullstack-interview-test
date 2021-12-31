import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import Home from "../views/Home";
import PullRequest from "../views/PullRequest";
import Repository from "../views/Repository";
import NavBar from "../components/NavBar";

const Routes = () => {
  const homeColor = "#040d21";
  const restPagesColor = "#0d1117";

  return (
    <Router>
      <div>
        <NavBar />

        <Switch>
          <Route
            path="/repository"
            render={() => {
              document.body.style.backgroundColor = restPagesColor;
              return <Repository />;
            }}
          />
          <Route
            path="/pullrequest"
            render={() => {
              document.body.style.backgroundColor = restPagesColor;
              return <PullRequest />;
            }}
          />
          <Route
            exact
            path="/"
            render={() => {
              document.body.style.backgroundColor = homeColor;
              return <Home />;
            }}
          />
        </Switch>
      </div>
    </Router>
  );
};

export default Routes;
