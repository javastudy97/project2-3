
//오늘 날짜출력
$(document).ready(function () {

  function convertTime() {
      let now = new Date();

      let year = now.getFullYear();
      let month = now.getMonth() + 1;
      let date = now.getDate();
      let weekday = new Array(7);
      weekday[0] = "일";
      weekday[1] = "월";
      weekday[2] = "화";
      weekday[3] = "수";
      weekday[4] = "목";
      weekday[5] = "금";
      weekday[6] = "토";

      let n = weekday[now.getDay()];

      return year +'년 '+ month + '월 ' + date + '일(' + n + ')';
  }

  let currentTime = convertTime();
  $('.nowtime').append(currentTime);
});


//제이쿼리사용
$.getJSON('https://api.openweathermap.org/data/2.5/weather?q=Seoul,kr&appid=5a87979705c7dd0e87fc7cfda0976f92&lang=kr&units=metric',
function (WeatherResult) {

  // 파라미터
  // q : 도시명
  // appid : apikey (발급필요)
  // lang : 언어 (kr : 한국어)
  // units : 온도표시 방식 (metric : 섭씨)

  // 날씨정보 출력
  $('.SeoulWeatherDesc').append(WeatherResult.weather[0].description);
  $('.SeoulNowtemp').append(WeatherResult.main.temp);
  $('.SeoulLowtemp').append(WeatherResult.main.temp_min);
  $('.SeoulHightemp').append(WeatherResult.main.temp_max);

  //날씨아이콘출력
  //WeatherResult.weater[0].icon
  let weathericonUrl =
      '<img src= "http://openweathermap.org/img/wn/'
      + WeatherResult.weather[0].icon +
      '.png" alt="' + WeatherResult.weather[0].description + '"/>'

  $('.SeoulIcon').html(weathericonUrl);
});