
/* 사이드바 열고 닫는 JS  */
    function Sidebar_move() { 
        var sidebar = document.getElementById("sidebar");
        var mainContent = document.getElementById("main");
            
        if (sidebar.style.left === "0px") {
            sidebar.style.left = "-220px";
            mainContent.style.marginLeft = "0";
        } else {
            sidebar.style.left = "0";
            mainContent.style.marginLeft = "220px";
        }
    }    

/* 탭 */
    document.addEventListener("DOMContentLoaded", function() {
        var tabs = document.querySelectorAll('.tab');
        var tabContents = document.querySelectorAll('.tab-content');

        tabs.forEach(tab => {
            tab.addEventListener('click', () => {
                var target = tab.getAttribute('data-tab');

             // 탭 선택 시 다른 탭의 내용 삭제 
             tabContents.forEach(content => content.classList.remove('active'));

             // 클릭한 탭과 해당 콘텐츠에 'active' 클래스 추가
             tab.classList.add('active');
             document.getElementById(target).classList.add('active');
            });
        });

        // 기본적으로 첫 번째 탭을 활성화
        tabs[0].classList.add('active');
        tabContents[0].classList.add('active');
    });

/* 타이머 실행 스크립*/

var timerbtn = document.querySelector(".timer-btn")

timerbtn.addEventListener("click", ()=> {

    const timerWindow = window.open(
        "timer.html", // 타이머 실행 html
        "타이머", // 창 이름
        "width=400,height=300" // 창 크기 
    )

    if (!timerWindow) {
        alert("팝업 차단을 해주세요! 타이머 창을 열 수 없습니다.")
    }
});

// 알림 팝업 열기 
const popupOverlay = document.querySelector(".popup-overlay");

const tab2writer = document.querySelector("#tab2-2") 
const popup2 = document.querySelector("#popup2");

tab2writer.addEventListener("click", () => {
popupOverlay.style.display = "block"; // overlay 배경 표시 
popup2.style.display = "block";       // 팝업 표시
});

// 회고록 팝업 열기
const tab3writer = document.querySelector("#write-tab") 
const popup3 = document.querySelector("#popup3");

tab3writer.addEventListener("click", () => {
popupOverlay.style.display = "block"; // overlay 배경 표시 
popup3.style.display = "block";       // 팝업 표시
});    

// 팝업 닫기 
const closePopup = () => {
popupOverlay.style.display = "none"; // overlay 배경 숨김 
popup2.style.display = "none";     // 알림 팝업 닫기
popup3.style.display = "none";     // 회고록 팝업 닫기 
};

popupOverlay.addEventListener("click", closePopup);   // overlay (창 밖 부분) 클릭 시 팝업 닫힘 

// 회고록 날짜
const currentDate = new Date(); // 현재 날짜 

const currentDateElement = document.getElementById("current-date");
const studycontent = document.getElementById("study-content") 
const prevdate = document.getElementById("prev-date") 
const nextdate = document.getElementById("next-date") 
const popupdate = document.getElementById("popup-date");


function formatDate(date) {
  return `${date.getFullYear()}.${String(date.getMonth() + 1).padStart(2, "0")}.${String(date.getDate()).padStart(2, "0")}`;
};

const records = {}; 

function updateDateDisplay() {
  currentDateElement.textContent = formatDate(currentDate);
  const recordKey = formatDate(currentDate);
  studycontent.textContent = records[recordKey] || "회고록을 작성해주세요.";
};

// 이전날짜 회고록 
prevdate.addEventListener("click", ()=> {
    currentDate.setDate(currentDate.getDate() -1);
    updateDateDisplay();
});

// 이후날짜 회고록
nextdate.addEventListener("click", ()=> {
    currentDate.setDate(currentDate.getDate() +1);
    updateDateDisplay();
});

// 작성 버튼
const uploadbtn = document.querySelector(".upload");

// 회고록 작성 시 달력에 표시
uploadbtn.addEventListener("click", (event) => {
event.preventDefault();

const title = document.getElementById("title").value;
const content = document.getElementById("content").value;
const recordKey = formatDate(currentDate);

if (title && content) {
    // 회고록 데이터 저장
    records[recordKey] = { title, content };

    alert("회고록이 저장되었습니다!");
    closePopup(); // 팝업 닫기
    updateDateDisplay(); // 회고록 내용 갱신
    updateCalendar(currentDate); // 달력 갱신
} else {
    alert("제목과 내용을 모두 입력해주세요.");
}
});

// 타이머 
var timerbtn = document.querySelector(".timer-btn")

timerbtn.addEventListener("click", ()=> {
    const timerWindow = window.open(
    "/html/studytimer.html", // 타이머 실행 (타이머 html)
    "타이머", // 타이머로 열 창 이름
    "width=400,height=300" // 타이머 창 크기 
    )

    if (!timerWindow) {
    alert("팝업 차단을 해주세요! 타이머 창을 열 수 없습니다.")
    }
});

// 달력
const calendarcontent = document.getElementById("days-content");
const monthyear = document.getElementById("month-year");
const prevmonth = document.getElementById("prev-month");
const nextmonth = document.getElementById("next-month");

// 달력 업데이트 함수
function updateCalendar(date) {
    const year = date.getFullYear();
    const month = date.getMonth();

    monthyear.textContent = `${year}년 ${month + 1}월`;

    const firstDay = new Date(year, month, 1).getDay(); // 이번 달 1일의 요일
    const lastDate = new Date(year, month + 1, 0).getDate(); // 이번 달의 마지막 날짜

    calendarcontent.innerHTML = ""; // 기존 내용 초기화

// 빈 칸 추가
    for (let i = 0; i < firstDay; i++) {
        const emptyDiv = document.createElement("div");
        emptyDiv.classList.add("empty");
        calendarcontent.appendChild(emptyDiv);
    }

// 날짜 추가
    for (let day = 1; day <= lastDate; day++) {
        const dayDiv = document.createElement("div");
        dayDiv.textContent = day;

        const recordKey = `${year}.${String(month + 1).padStart(2, "0")}.${String(day).padStart(2, "0")}`;

        // 회고록 작성 날짜
        if (records[recordKey]) {
            dayDiv.classList.add("has-record");
        }

        calendarcontent.appendChild(dayDiv);
    }

};

// 이전 달 버튼
prevmonth.addEventListener("click", () => {
    currentDate.setMonth(currentDate.getMonth() - 1);
    updateCalendar(currentDate);
});

// 다음 달 버튼
nextmonth.addEventListener("click", () => {
    currentDate.setMonth(currentDate.getMonth() + 1);
    updateCalendar(currentDate);
});

// 초기화
updateCalendar(currentDate);
updateDateDisplay(); // 회고록 날짜 초기화


// 출석률 차트 
var data = {
    value: 70,
    max: 100,
    label: "출석률"
};

var chartCtx = document.getElementById('gaugeChart').getContext('2d');

var config = {
    type: 'doughnut',
    data: {
        labels: [data.label],
        datasets: [{
            data: [data.value, data.max - data.value],
            backgroundColor: ['#f0e53c', 'rgba(0, 0, 0, 0.1)'],
            borderWidth: 0
        }]
    },

    options: { 
        responsive: true, // 차트 반응형 여부 설정 
        //maintainAspectRatio: false, // 가로세로 비율 유지 X
        cutoutPercentage: 85, // 도넛형 차트 가운데 비어있는 크기 설정 
        rotation: -90, 
        circumference: 180, 
        animation: {
            animateRotate: true,
            animateScale: false
        },
        title: {
            display: true,
            text: data.label,
            fontSize: 16
        }}
        
    };

    var gaugeChart = new Chart(chartCtx, config);
    

    
