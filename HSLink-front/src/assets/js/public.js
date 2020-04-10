const  getDate = function() {
  let day2 = new Date();
  day2.setTime(day2.getTime());
  let month;
  let day;
  let hours;
  let minutes;
  if(day2.getMonth() < 9 ) {
    month = "0"+(day2.getMonth()+1)
  } else {
    month = (day2.getMonth()+1)
  }
  if (day2.getDate() < 10) {
    day = "0"+day2.getDate();
  } else {
    day = day2.getDate();
  }
  if (day2.getHours() < 10) {
    hours = "0"+day2.getHours();
  } else {
    hours = day2.getHours();
  }
  if (day2.getMinutes() < 10) {
    minutes = "0"+day2.getMinutes();
  } else {
    minutes = day2.getMinutes();
  }
  return  day2.getFullYear()+"-" + month + "-" + day+" "+hours+":"+minutes
}
 export { getDate }
