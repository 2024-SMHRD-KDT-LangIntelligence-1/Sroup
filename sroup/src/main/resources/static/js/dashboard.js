
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


    

    
