const NameRepeater = {
    name: "My Name",
    setName: function setName(name) {
      // NameRepeater의 이름을 세팅합니다.
      this.name=name;
    },
    repeatName: function repeatName(num) {
      // NameRepeater의 이름을 num 만큼 반복해 리턴합니다.
      return this.name.repeat(num);
    },
  };
  
  export default NameRepeater;
  