let timer;
  let isRunning = false;
  let startTime; // 시작 시간 (타임스탬프)

  const startbtn = document.querySelector(".start-btn");
  const stopbtn = document.querySelector(".stop-btn");
  const timerDisplay = document.querySelector(".time");
  const learningTime = document.querySelector(".learning-time");

  // 타이머 시작  
  function startTimer() {
    if (!isRunning) {
      isRunning = true;
      startTime = new Date().getTime(); // 자바스크립트의 현재 시간을 타임스탬프로 반환해 시작 시간 저장
      timer = setInterval(updateTimer, 1000);
      startbtn.disabled = true;
      stopbtn.disabled = false;
  }
}

  // 타이머 정지 
  function stopTimer() {
    if (isRunning) {
      isRunning = false;
      clearInterval(timer);
      const endTime = new Date().getTime(); // 타임스탬프로 시간 저장

      // 경과 시간 계산 및 기록 추가
      const duration = calculateDuration(startTime, endTime); // 시작 시간, 끝 시간 간격 계산하여 duration 에 담기 
      recordTime(startTime, endTime, duration);

      sendTimeData(startTime, endTime); // 서버로 데이터 전송

      startbtn.disabled = false;
      stopbtn.disabled = true;
    }
  }

  // 타이머 업데이트 
  function updateTimer() {
    const currentTime = new Date().getTime(); // 현재시간 타임스탬프로 변수에 저장
    const duration = calculateDuration(startTime, currentTime); 
    timerDisplay.textContent = duration;
    
  }

  // 타이머 기록 추가 
  function recordTime(start, end, duration) {
    const record = document.createElement('div');
    record.textContent = `시작: ${formatTimestamp(start)} - 종료: ${formatTimestamp(end)} (총 ${duration})`;
    learningTime.appendChild(record);
  }
  

  // 타임스탬프를 날짜와 시간 문자열로 변환
  function formatTimestamp(timestamp) {
    return new Date(timestamp).toLocaleString('ko-KR', {
      year: 'numeric',
      month: '2-digit',
      day: '2-digit',
      hour: '2-digit',
      minute: '2-digit',
      second: '2-digit'
    }).replace(/\./g, '-').replace(' ', ' ');
}

// 날짜와 시간을 함께 포맷
function formatTime(date) {
  return date.toLocaleString('ko-KR', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit'
  }).replace(/\./g, '-').replace(' ', ' ');
}

// 타임스탬프를 사용한 경과 시간 계산
function calculateDuration(startTimestamp, endTimestamp) {
  const durationSeconds = Math.floor((endTimestamp - startTimestamp) / 1000); // 초 단위로 변환
  const h = Math.floor(durationSeconds / 3600);
  const m = Math.floor((durationSeconds % 3600) / 60);
  const s = durationSeconds % 60;

  return `${String(h).padStart(2, '0')}:${String(m).padStart(2, '0')}:${String(s).padStart(2, '0')}`;
}

startbtn.addEventListener('click', startTimer);
stopbtn.addEventListener('click', stopTimer);
stopbtn.disabled = true;


  // 서버로 데이터 전송
  function sendTimeData(startTime, endTime) {
    const timeData = {
      st_tm: startTime,    // 시작 시간 (타임스탬프)
      ed_tm: endTime       // 종료 시간 (타임스탬프)
    };
  
    fetch('/api/timer/save', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(timeData)
    })
      .then(response => response.json())
      .then(data => console.log('서버 응답:', data))
      .catch(error => console.error('오류 발생:', error));
  }
  