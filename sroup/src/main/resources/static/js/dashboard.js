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


/* ---- 타이머 실행 스크립 ---- */
document.addEventListener("DOMContentLoaded", () => {
    const timerbtn = document.querySelector(".timer-btn");

    timerbtn.addEventListener("click", () => {
        const timerWindow = window.open(
            "/html/studytimer.html", // 타이머 실행 html
            "타이머", // 창 이름
            "width=600,height=500" // 창 크기 
        );

        if (!timerWindow) {
            alert("팝업 차단을 해제해주세요! 타이머 창을 열 수 없습니다.");
        }
    });
});


/* ---- 알림 팝업 열기 ---- */

document.addEventListener("DOMContentLoaded", ()=> {
	const tab2popupOverlay = document.querySelector("#tab2-popupoverlay");
	const tab3popupOverlay = document.querySelector("#tab3-popupoverlay");
	const tab2writer = document.querySelector("#tab2-2") 
	const popup2 = document.querySelector("#popup2");
	
	if(tab2popupOverlay && tab2writer && popup2) {
		tab2writer.addEventListener("click", () => {
		tab2popupOverlay.style.display = "block"; // overlay 배경 표시 
			popup2.style.display = "block";       // 팝업 표시
		});
		
		tab2popupOverlay.addEventListener("click", ()=> closePopup(tab2popupOverlay, popup2));
	} else {
		console.error("Tab2 관련 요소가 DOM에 존재하지 않습니다.");
	}
	
	/* 회고록 팝업 열기 */
	const tab3writer = document.querySelector("#write-tab") 
	const popup3 = document.querySelector("#popup3");

	if(tab3popupOverlay && tab3writer && popup3 ) {
		tab3writer.addEventListener("click", () => {
			tab3popupOverlay.style.display = "block"; // overlay 배경 표시 
			popup3.style.display = "block";       // 팝업 표시
		});
		
		tab3popupOverlay.addEventListener("click", ()=> closePopup(tab3popupOverlay,popup3));
	} else {
		console.error("Tab2 관련 요소가 DOM에 존재하지 않습니다.");
	}

});



/* 팝업 닫기 */ 
const closePopup = (overlay,popup) => {
	overlay.style.display = "none"; // 오버레이 배경 숨김 
	popup.style.display = "none";     // 팝업 닫기
};


document.addEventListener("DOMContentLoaded", () => {
  const currentDate = new Date(); // 현재 날짜

  const currentDateElement = document.getElementById("current-date");
  const studyContent = document.getElementById("study-content");
  const prevDate = document.getElementById("prev-date");
  const nextDate = document.getElementById("next-date");
  const calendarContent = document.getElementById("days-content");
  const monthYear = document.getElementById("month-year");
  const prevMonth = document.getElementById("prev-month");
  const nextMonth = document.getElementById("next-month");
  const uploadBtn = document.querySelector(".upload");
  const popupOverlay = document.querySelector("#tab3-popupoverlay");
  const popup = document.querySelector("#popup3");

  if (!currentDateElement || !studyContent || !prevDate || !nextDate || !calendarContent || !monthYear || !uploadBtn || !popupOverlay || !popup) {
    console.error("필수 DOM 요소가 누락되었습니다.");
    return;
  }

  const records = {};

  function formatDate(date) {
    return `${date.getFullYear()}.${String(date.getMonth() + 1).padStart(2, "0")}.${String(date.getDate()).padStart(2, "0")}`;
  }

  function updateDateDisplay() {
    currentDateElement.textContent = formatDate(currentDate);
    const recordKey = formatDate(currentDate);
    const record = records[recordKey];

    if (record) {
      studyContent.innerHTML = ""; // 기존 내용을 초기화
      const titleElem = document.createElement("h3");
      titleElem.textContent = record.title;

      const contentElem = document.createElement("p");
      contentElem.textContent = record.content;

      studyContent.appendChild(titleElem);
      studyContent.appendChild(contentElem);
    } else {
      studyContent.textContent = "회고록을 작성해주세요.";
    }
  }

  function updateCalendar(date) {
    const year = date.getFullYear();
    const month = date.getMonth();

    monthYear.textContent = `${year}년 ${month + 1}월`;

    const firstDay = new Date(year, month, 1).getDay(); // 이번 달 1일의 요일
    const lastDate = new Date(year, month + 1, 0).getDate(); // 이번 달의 마지막 날짜

    calendarContent.innerHTML = ""; // 기존 내용 초기화

    // 빈 칸 추가
    for (let i = 0; i < firstDay; i++) {
      const emptyDiv = document.createElement("div");
      emptyDiv.classList.add("empty");
      calendarContent.appendChild(emptyDiv);
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

      calendarContent.appendChild(dayDiv);
    }
  }

  // 이전 날짜 회고록
  prevDate.addEventListener("click", () => {
    currentDate.setDate(currentDate.getDate() - 1);
    updateDateDisplay();
  });

  // 이후 날짜 회고록
  nextDate.addEventListener("click", () => {
    currentDate.setDate(currentDate.getDate() + 1);
    updateDateDisplay();
  });

  // 작성 버튼
  uploadBtn.addEventListener("click", (event) => {
    const title = document.getElementById("title").value;
    const content = document.getElementById("content").value;
    const recordKey = formatDate(currentDate);

    if (title && content) {
      // 회고록 데이터 저장
      records[recordKey] = { title, content };

      alert("회고록이 저장되었습니다!");
      popupOverlay.style.display = "none";
      popup.style.display = "none";
      updateDateDisplay(); // 회고록 내용 갱신
      updateCalendar(currentDate); // 달력 갱신
    } else {
      event.preventDefault();
      alert("제목과 내용을 모두 입력해주세요.");
    }
  });

  // 달력 이전/다음 버튼
  prevMonth.addEventListener("click", () => {
    currentDate.setMonth(currentDate.getMonth() - 1);
    updateCalendar(currentDate);
  });

  nextMonth.addEventListener("click", () => {
    currentDate.setMonth(currentDate.getMonth() + 1);
    updateCalendar(currentDate);
  });

  // 초기화
  updateCalendar(currentDate);
  updateDateDisplay(); // 회고록 날짜 초기화
});


/* ---- 출석률 차트 ---- */
document.addEventListener("DOMContentLoaded", () => {
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
				responsive: true,
				cutoutPercentage: 85,
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
				}
			}
		};
		
		new Chart(chartCtx, config);
	});


		
// 리뷰 팝업
function reviewpopup() {
	window.open(
	"/html/review.html", 
	"RIVIEW", 
	"width=800, height=1300"
	);
}

// progress chart (달성률 차트)
document.addEventListener("DOMContentLoaded", ()=> {
	const gresschart = document.getElementById("progresschart").getContext("2d");

	// 진행률 데이터 
	const gressvalue = 70;

	const progresschart = new Chart(gresschart, {
		type: "bar",
		data: {	
			labels: ['진행률'], // X축 라벨
			datasets: [{
			data: [gressvalue], // 진행률 값 
			backgroundColor: ['#4CAF50'], // 진행된 부분 색상
			borderColor: ['#4CAF50'], // 진행된 부분 테두리 색상
			borderWidth: 1,
			barThickness: 30, // 막대 두께
			}]
		},

		options: {
			indexAxis: 'y', // 수평 막대 차트
			responsive: true,
			plugins: {
				legend: { display: false }, // 범례 비활성화
				tooltip: { enabled: false } // 툴팁 비활성화
			},
			scales: {
				x: {
					max: 100, // 100% 기준
					beginAtZero: true, // 0%에서 시작
					grid: { display: false }, // X축 격자 제거
					ticks: { display: false } // X축 숫자 숨김
				},
				y: {
					grid: { display: false }, // Y축 격자 제거
					ticks: { display: false } // Y축 라벨 숨김
				}}         
		}
	})
});

/* ---- 마인드맵 생성 ---- */
let uploadedFiles = {}; // Store uploaded files per node
let nodeLabels = {}; // Store node labels by ID

// Initialize Mindmap
document.addEventListener('DOMContentLoaded', function() {
	var cy = cytoscape({
		container: document.getElementById('mindmap-container'),
		elements: [
			{ data: { id: 'a', label: '파이썬 완전정복!' } },
			{ data: { id: 'b', label: '파이썬 설치' } },
			{ data: { id: 'c', label: '숫자와 문자열 배우기' } },
			{ data: { id: 'd', label: '조건문' } },
		    { data: { id: 'e', label: '반복문-세부1' } },
		    { data: { id: 'b-1', label: '반복문-세부2' } },
			{ data: { id: 'b-2', label: '반복문-세부3' } },
			{ data: { id: 'c-1', label: '함수' } },
			{ data: { id: 'c-2', label: '예상문제 출제' } },
			{ data: { id: 'd-1', label: '예상문제 풀기' } },
			{ data: { id: 'd-2', label: '채점' } },
			{ data: { id: 'b-1-1', label: '내용정리' } },
			{ data: { id: 'c-1-1', label: '참고 사이트 정리' } },
			{ data: { id: 'c-1-2', label: '참고 유튜브 정리' } },
			{ data: { id: 'd-1-1', label: '오답풀이' } },
			{ data: { source: 'a', target: 'b' } },
			{ data: { source: 'a', target: 'c' } },
			{ data: { source: 'a', target: 'd' } },
			{ data: { source: 'a', target: 'e' } },
			{ data: { source: 'b', target: 'b-1' } },
			{ data: { source: 'b', target: 'b-2' } },
			{ data: { source: 'c', target: 'c-1' } },
			{ data: { source: 'c', target: 'c-2' } },
			{ data: { source: 'd', target: 'd-1' } },
			{ data: { source: 'd', target: 'd-2' } },
			{ data: { source: 'b-1', target: 'b-1-1' } },
			{ data: { source: 'c-1', target: 'c-1-1' } },
			{ data: { source: 'c-1', target: 'c-1-2' } },
			{ data: { source: 'd-1', target: 'd-1-1' } },
		],
		style: [{
			selector: 'node',
			style: {
				'content': 'data(label)',
				'text-valign': 'center',
				'color': 'black',
				'background-color': '#ebd515',
				'width': 70,
				'height': 70,
				'font-size': '12px'
			}},
			{selector: 'edge',
				style: {
					'width': 3,
					'line-color': '#00aaff',
					'target-arrow-color': '#00aaff',
					'target-arrow-shape': 'triangle', /* 화살표 모양 */
					'curve-style': 'bezier'
				}
			}
		],
		layout: {
			name: 'breadthfirst',
			directed: true,       // 방향을 지정
			padding: 10,          // 여백
			roots: '#a',          // 시작 노드 지정
			orientation: 'left-to-right' // 방향 설정 (좌 → 우)
		},
		
		// 확대/축소 및 드래그 비활성화 설정
		userZoomingEnabled: false, // 사용자가 확대/축소할 수 없게 설정
	});
	
	// 노드 클릭 이벤트 추가
	userZoomingEnabled: false, // 사용자가 확대/축소할 수 없게 설정
	cy.nodes().forEach(node => {
		nodeLabels[node.id()] = node.data('label');
		createNodeDashboard(node.id());
	});

	// Add listener for adding new nodes
	cy.on('add', 'node', function(evt) {
		const newNode = evt.target;
		nodeLabels[newNode.id()] = newNode.data('label');
		createNodeDashboard(newNode.id());
	});
});

function createNodeDashboard(nodeId) {
	const dashboardList = document.getElementById('dashboard-list');

	// Create individual dashboard
	const dashboard = document.createElement('div');
	dashboard.id = `dashboard-${nodeId}`;
	dashboard.className = 'node-dashboard';

	// Title
	const title = document.createElement('h5');
	title.innerText = `Node ${nodeLabels[nodeId]} Dashboard`;
	dashboard.appendChild(title);

	// File upload input
	const fileInput = document.createElement('input');
	fileInput.type = 'file';
	fileInput.onchange = function(event) {
		const file = event.target.files[0];
		if (file) {
			if (!uploadedFiles[nodeId]) {
				uploadedFiles[nodeId] = [];
			}
		uploadedFiles[nodeId].push(file);
		updateUploadedFilesList(nodeId);
		}
	};

	dashboard.appendChild(fileInput);

	 // Uploaded files list
	const filesContainer = document.createElement('div');
	filesContainer.id = `uploaded-files-${nodeId}`;
	dashboard.appendChild(filesContainer);

	// Save button
	const saveButton = document.createElement('button');
	saveButton.className = 'save-button';
	saveButton.innerText = 'Save Files';
	saveButton.onclick = function() {
		saveAllFiles(nodeId);
	};
	dashboard.appendChild(saveButton);

	dashboardList.appendChild(dashboard);
};

function updateUploadedFilesList(nodeId) {
	const filesContainer = document.getElementById(`uploaded-files-${nodeId}`);
	filesContainer.innerHTML = '';
	if (uploadedFiles[nodeId] && uploadedFiles[nodeId].length > 0) {
		uploadedFiles[nodeId].forEach((file, index) => {
		const fileLink = document.createElement('a');
		fileLink.href = URL.createObjectURL(file);
		fileLink.download = file.name;
		fileLink.innerText = `(${nodeLabels[nodeId]}) ${index + 1}. ${file.name}`;
		fileLink.style.display = 'block';
		filesContainer.appendChild(fileLink);
	});
	} else {
		filesContainer.innerHTML = '<p>No files uploaded for this node.</p>';
	}
};

function saveAllFiles(nodeId) {
	if (!uploadedFiles[nodeId] || uploadedFiles[nodeId].length === 0) {
		alert('No files to save for this node.');
		return;
	}

	uploadedFiles[nodeId].forEach(file => {
		const formData = new FormData();
		formData.append('nodeId', nodeId);
		formData.append('file', file);

		fetch('/save-file', {
		method: 'POST',
		body: formData
		})
		.then(response => response.json())
		.then(data => {
			console.log('File saved successfully:', data);
		})
		.catch(error => {
			console.error('Error saving file:', error);
		});
	});

	alert('All files have been saved.');
}

/* 캘린더 생성 */
document.addEventListener('DOMContentLoaded', function() {
	var calendarEl = document.getElementById('calendar-container');
	var calendar = new FullCalendar.Calendar(calendarEl, {
		initialView: 'dayGridMonth',
		events: [
			{ title: '정보처리기사 자격증 취득', start: '2024-12-05' },
			{ title: '책 구매', start: '2024-12-10' },
			{ title: '1~5강 마스터', start: '2024-12-15' },
			{ title: '6~10강 마스터', start: '2024-12-20' },
			{ title: '필기시험 모의고사', start: '2025-01-05' },
			{ title: '필기시험', start: '2025-01-11' },
			{ title: '실기 1~5강 마스터', start: '2025-01-18' },
			{ title: '실기 6~10강 마스터', start: '2025-01-25' },
			{ title: '실기시험 모의고사', start: '2025-01-31' },
		]
	});
	
	calendar.render();

});
		    

// 마인드맵노드 클릭 이벤트 추가
		    
		    

/* 멤버 리스트 기능 */
function showDetailDashboard(name, location, group, attendance, achievement) {
	document.getElementById('detail-name').innerText = `이름: ${name}`;
	document.getElementById('detail-location').innerText = `위치: ${location}`;
	document.getElementById('detail-group').innerText = `가입한 그룹: ${group}`;
	document.getElementById('detail-attendance').innerText = `출석률: ${attendance}`;
	document.getElementById('detail-achievement').innerText = `달성률: ${achievement}`;
	document.getElementById('detail-dashboard').style.display = 'block';
}

function closeDetailDashboard() {
	document.getElementById('detail-dashboard').style.display = 'none';
}

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

function removeMember() {
	alert('회원이 내보내졌습니다.');
}


    
