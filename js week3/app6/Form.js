const Form = () => {
    const formState = {};
  
    function register(name, validator = (value) => true) {
      // register시, state에 필드를 등록합니다.
      // 필드 등록 객체는 { value, validator } 입니다.
      // value는 빈 문자열로 초기화됩니다.
      formState[name]={ value: "", validator
      }
    }
  
    function validate() {
      // state의 전체 필드를 검사합니다.
      // validator(value) 로 value가 유효한지 검사할 수 있습니다.
      // 전체 필드가 유효해야만 폼이 유효합니다.
      return Object
          .values(formState)
          .reduce(
          (flag,{value,validator}) => validator(value) && flag, true
          )
    }
  
    function getFormData() {
      // state의 각 필드에 있는 value를 모아 하나의 객체로 리턴합니다.
      // { name : 'Kim', age: 30 } 의 형식으로 리턴해야 합니다.    
      return Object
          .entries(formState)
          .reduce((formData, item) => {
              const [key,{value, validator}]=item
              formData[key]=value
              return formData
          }, {})
    }
  
    function setValue(name, value) {
      // name으로 찾은 필드의 value를 설정합니다.
      // name에 해당하는 상태는 반드시 있다고 가정합니다.
      formState[name]={
          ...formState[name],
          value
      }
    }
  
    return {
      register,
      validate,
      getFormData,
      setValue,
    };
  };
  
  export default Form;
  