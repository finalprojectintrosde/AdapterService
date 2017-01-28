<html>
<body>
	<h2>BMI API</h2>
	URL: <a href="/Bmi">/Bmi?weight=[weight]&height=[height]&sex=[m|f]&age=[age]</a> Source: <a href="https://market.mashape.com/nviror/bmi-calculator">mashape</a><br>
	Example Response: {"value":"29.4","status":"Overweight","risk":"High risk of developing heart disease, high blood pressure, stroke, diabetes","prime":"1.3"}
	<h2>Weather API</h2>
	URL: <a href="/Weather">/Weather?lat=[latitude]&lng=[longitude]</a> Source: <a href="https://market.mashape.com/fyhao/weather-13#weatherdata">mashape</a><br>
	Example Response: {"date":"Sun, 15 Jan 2017 09:00 AM CET","temp":"-9","code":"26","text":"Cloudy"}
	<br>
	<br>
	URL: <a href="/WeatherForecast">/WeatherForecast?lat=[latitude]&lng=[longitude]</a> Source: <a href="https://market.mashape.com/fyhao/weather-13#weatherdata">mashape</a><br>
	Example Response: [{"date":"15 Jan 2017","high":"-6","code":"30","low":"-11","text":"Partly Cloudy","day":"Sun"}, ...]
	<h2>Fitbit API</h2>
	URL: <a href="/FitbitActivities">/FitbitActivities?access_token=[]&user_id=[]&refresh_token[]</a> Source: <a href="https://dev.fitbit.com/docs/activity/#browse-activity-types">Fitbit Developer</a><br>
	Example Response: [{"accessLevel":"PUBLIC","hasSpeed":false,"id":3015,"mets":6.5,"name":"Aerobic, general"}, ...]
	<br>
	<br>
	URL: <a href="/FitbitDailyActivitySummary">/FitbitDailyActivitySummary?access_token=[]&user_id=[]&refresh_token[]&date=[yyyy-MM-dd]</a> Source: <a href="https://dev.fitbit.com/docs/activity/#get-daily-activity-summary">Fitbit Developer</a><br>
	Example Response: {"activities":[{"activityId":51007,"activityParentId":90019,"calories":230,"description":"7mph","distance":2.04,"duration":1097053,"hasStartTime":true,"isFavorite":true,"logId":1154701,"name":"Treadmill, 0% Incline","startTime":"00:25","steps":3783}, ...],"goals":{"caloriesOut":2826,"distance":8.05,"floors":150,"steps":10000},"summary":{"caloriesOut":2143}}
	<br>
	<br>
	URL: <a href="/FitbitBodyGoal">/FitbitBodyGoal?access_token=[]&user_id=[]&refresh_token[]</a> Source: <a href="https://dev.fitbit.com/docs/body/#get-body-goals">Fitbit Developer</a><br>
	Example Response: {"startDate":"2015-01-15","startWeight":"150","weight":"140"}
	<br>
	<br>
	URL: <a href="/FitbitWeightLogs">/WeightLogs?access_token=[]&user_id=[]&refresh_token[]&base_date=[yyyy-MM-dd]&end_date=[yyyy-MM-dd]</a> Source: <a href="https://dev.fitbit.com/docs/body/#get-weight-logs">Fitbit Developer</a><br>
	Example Response: [{"bmi":23.57,"date":"2015-03-05","logId":1330991999000,"time":"23:59:59","weight":73,"source":"API"},{"bmi":22.57,"date":"2015-03-05","logId":1330991999000,"time":"21:10:59","weight":72.5,"source":"Aria"}]
	<br>
	<br>
	URL: <a href="/FitbitFavoriteActivities">/FitbitFavoriteActivities?access_token=[]&user_id=[]&refresh_token[]</a> Source: <a href="https://dev.fitbit.com/docs/activity/#get-favorite-activities">Fitbit Developer</a><br>
	Example Response: [{"activityId":12030,"description":"5 mph (12 min/mile)","mets":8,"name":"Run"}, ...]
	<br>
	<br>
	URL: <a href="/FitbitProfile">/FitbitProfile?access_token=[]&user_id=[]&refresh_token[]</a> Source: <a href="https://dev.fitbit.com/docs/user/#get-profile">Fitbit Developer</a><br>
	Example Response: {"height":178.0,"weight":70.5}
</body>
</html>