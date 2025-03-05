import "./app.css";
import arrayFunctions from "./Component";

const App = () => {
  const currentArray = [1, 2, 3, 4, 5];

  const mapButton = document.getElementById("map-button");
  const filterButton = document.getElementById("filter-button");
  const reduceButton = document.getElementById("reduce-button");
  const result = document.getElementById("result");
  const initialValue = document.getElementById("initial-value");

  initialValue.innerHTML = `초기 배열 값 : ${JSON.stringify(currentArray)}`;

  mapButton.addEventListener("click", () => {
    const newArray = arrayFunctions.map(currentArray, (item) => item * 2);
    result.innerHTML = `map 함수 결과 : ${JSON.stringify(newArray)}`;
  });

  filterButton.addEventListener("click", () => {
    const newArray = arrayFunctions.filter(currentArray, (item) => item < 2);
    result.innerHTML = `filter 함수 결과 : ${JSON.stringify(newArray)}`;
  });

  reduceButton.addEventListener("click", () => {
    const newArray = arrayFunctions.reduce(
      currentArray,
      (left, right) => left + right,
      0
    );
    result.innerHTML = `reduce 함수 결과 : ${JSON.stringify(newArray)}`;
  });
};

module.exports = App;
