const arrayFunctions = {
    map(array, func) {
      // map 함수를 구현하세요.
      // map 함수는 배열의 각 원소를 변환한 새로운 배열을 반환합니다.
      // func - (현재 아이템) => 변환된 아이템 형식입니다.
      // ex) item => item * 2 함수는 배열의 모든 원소에 *2를 적용합니다.
      // array의 내장 map 함수를 쓰지 않고, for문을 이용해 구현해보세요.
      let newArray = [];
      for(let i=0;i<array.length;i++){
          newArray.push(func(array[i]));   
      }
      return newArray;
    },
  
    filter(array, func) {
      // filter 함수를 구현하세요.
      // filter 함수는 배열의 각 원소 중 조건에 해당하는 원소만 들어있는 새로운 배열을 반환합니다.
      // func - (현재 아이템) => 조건(true or false) 형식입니다.
      // ex) => item => item > 5 함수는 5보다 큰 값들만을 반환합니다.
      // array의 내장 filter 함수를 쓰지 않고 구현해보세요.
  
      let newArray = [];
      for(let i=0;i<array.length;i++){
          if(func(array[i])){
              newArray.push(array[i]);
          }
      }
      return newArray;
    },
  
    reduce(array, func, initialValue) {
      // reduce 함수를 구현하세요.
      // reduce 함수는 배열의 각 원소를 함수에 맞게 합성해 하나의 값을 반환합니다.
      // func - (합쳐진 값, 현재 아이템) => '새로운 값' 의 형식입니다.
      // initialValue - 초기값입니다. 초기값은 반드시 주어져야 합니다.
      // ex) (acc, cur) => acc + cur 함수는 배열의 모든 원소를 합칩니다.
      // array의 내장 reduce 함수를 쓰지 않고 구현해보세요.
      let result = initialValue;
      for(let i=0;i<array.length;i++){
          result=func(result,array[i])
      }
      return result;
    },
  };
  
  export default arrayFunctions
  