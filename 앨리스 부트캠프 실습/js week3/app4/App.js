import "./app.css";
import Counter from "./Counter";

const App = () => {
  const counter = Counter();

  const increaseButton = document.getElementById("increase-button");
  const decreaseButton = document.getElementById("decrease-button");
  const currentCount = document.getElementById("current-count");

  increaseButton.addEventListener("click", () => {
    counter.increase();
    currentCount.innerHTML = counter.getCount();
  });

  decreaseButton.addEventListener("click", () => {
    counter.decrease();
    currentCount.innerHTML = counter.getCount();
  });
};

export default App;
