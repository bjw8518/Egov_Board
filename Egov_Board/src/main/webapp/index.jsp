<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메인 페이지</title>
    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/css/normalize.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/css/style.css">
    <style>
        body {
            overflow-x: hidden;
        }
        #sidebar {
            height: 100vh;
            position: fixed;
            top: 0;
            left: -250px;
            width: 250px;
            background-color: #343a40;
            color: white;
            padding-top: 20px;
            transition: all 0.3s;
            z-index: 1000;
        }
        #sidebar.active {
            left: 0;
        }
        #sidebar a {
            color: white;
            text-decoration: none;
            padding: 10px 15px;
            display: block;
        }
        #sidebar a:hover {
            background-color: #495057;
        }
        #content {
            transition: all 0.3s;
        }
        #content.active {
            margin-left: 250px;
        }
        #sidebarCollapse {
		    position: fixed;
		    top: 20px;
		    left: 20px;
		    z-index: 1001;
		    padding: 3px 6px;
		}
    </style>
</head>
<body>
    <!-- Sidebar Toggle Button -->
    <button id="sidebarCollapse" class="btn btn-primary">
        메뉴 열기
    </button>

    <!-- Sidebar -->
    <div id="sidebar">
        <h4 class="text-center">메뉴</h4>
        <a href="/main/index.do">홈</a>
        <a href="/bbs/list.do">게시판</a>
        <a href="/user/login.do">로그인</a>
        <a href="/user/signup.do">회원가입</a>
    </div>

    <!-- Main Content -->
    <div id="content">
        <!-- Top Bar with Login/Signup -->
        <div class="d-flex justify-content-end mb-4">
            <a href="/user/login.do" class="btn btn-outline-primary me-2">로그인</a>
            <a href="/user/signup.do" class="btn btn-primary">회원가입</a>
        </div>

        <!-- Welcome Section -->
        <div class="text-center mb-5">
            <h1>환영합니다!</h1>
            <p>이곳은 메인 페이지입니다.</p>
        </div>

        <!-- 게시판 프리뷰 -->
        <div class="card">
            <div class="card-header">
                게시판 프리뷰
                <a href="/bbs/list.do" class="btn btn-link float-end">더보기</a>
            </div>
            <ul class="list-group list-group-flush">
                <!-- 게시글 목록 (샘플 데이터) -->
                <li class="list-group-item">
                    <a href="/bbs/detail.do?id=1">첫 번째 게시글 제목입니다.</a>
                    <span class="text-muted float-end">2025-03-07</span>
                </li>
                <li class="list-group-item">
                    <a href="/bbs/detail.do?id=2">두 번째 게시글 제목입니다.</a>
                    <span class="text-muted float-end">2025-03-06</span>
                </li>
                <li class="list-group-item">
                    <a href="/bbs/detail.do?id=3">세 번째 게시글 제목입니다.</a>
                    <span class="text-muted float-end">2025-03-05</span>
                </li>
            </ul>
        </div>
    </div>

    <!-- JS -->
    <script src="${pageContext.request.contextPath}/resources/common/js/jquery-3.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/common/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/common/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/common/js/script.js"></script>

    <!-- Inline JavaScript for Sidebar -->
    <script>
        $(document).ready(function () {
            $('#sidebarCollapse').on('click', function () {
                $('#sidebar').toggleClass('active');
                $('#content').toggleClass('active');
                if ($('#sidebar').hasClass('active')) {
                    $(this).text('메뉴 닫기');
                } else {
                    $(this).text('메뉴 열기');
                }
            });
        });
    </script>
</body>
</html>
