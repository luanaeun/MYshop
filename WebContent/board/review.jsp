<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="ec-board-list-multiple-list-wrapper">
    <div class="reviewArea" async_module="smartreview_dispList">
        <!--
            $square_type = square
                ※ This is a required value when using a square type list. Do not delete or change it.

            $is_photo_only = F
                ※ T 포토리뷰만 보기 / F 모든리뷰 보기

            $more_view_type = scroll
                ※ scroll : 다음글을 스크롤로 제어
                   button : 다음글을 더보기 버튼으로 제어
                   paginate : 다음글을 페이지네이트로 제어

            $sns_popup_url = /board/smartreview/sns_popup.html

            $sort_order = recent
                ※ recent : 최신순으로 기본정렬
                   rating : 평점순으로 기본정렬
                   like : 좋아요순으로 기본정렬
                   hit : 조회수순으로 기본정렬
        -->
        <!-- 비동기로 처리전 노출되는 섹션 -->
        <div async_section="before">
            <div class="loading">
                <img src="//img.echosting.cafe24.com/skin/base/board/review/ico_loading.gif" alt="로딩중입니다. 잠시만 기다려 주세요" />
            </div>
        </div>
        <!-- 비동기로 처리된 이후 노출되는 섹션 -->
        <div async_section="after" style="display:none;">
            <div class="reviewTitle">
                <div class="path">
                    <span>현재 위치</span>
                    <ol>
                        <li><a href="/">홈</a></li>
                        <li title="현재 위치"><strong>상품리뷰</strong></li>
                    </ol>
                </div>
                <div class="title">
                    <h2>하</h2>
                </div>
            </div>
            <div class="reviewSearch">
                <ul class="sorting">
                    <li class="selected">
                    	<a href="#none" class="ec-board-list-sorting" data-sort-order="recent" data-sort-order-text="최신순">최신순 </a></li>
                    <li class="{$*display_rating_visible_class}"><a href="#none" class="ec-board-list-sorting" data-sort-order="rating" data-sort-order-text="평점순">평점순</a></li>
                    <li><a href="#none" class="ec-board-list-sorting" data-sort-order="like" data-sort-order-text="좋아요순">좋아요순</a></li>
                    <li><a href="#none" class="ec-board-list-sorting" data-sort-order="hit" data-sort-order-text="조회수순">조회수순</a></li>
                </ul>
                <div class="ctrl">
                    <label>포토리뷰만 보기</label>
                    <div class="searchWrap">
                        	ㅎㅎ
                        <div class="search">
                            <input type="text" class="keyword ec-board-list-keyword" value="{$*keyword}" placeholder="검색어 입력" />
                            <button type="submit" class="btnSearch ec-board-list-search-keyword">검색</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="reviewCategory">
                <fieldset>
                    <legend>분류</legend>
                    <p class="category">상품분류</p>
                </fieldset>
            </div>
            <div class="searchArea">
                <button type="button" class="btnOpen ec-board-list-search-detail {$*display_search_button_visible_class}">상세검색 열기</button>
                <div class="searchList ec-board-list-additems">
                    <table border="1" summary="">
                        <caption>상세검색</caption>
                        <colgroup>
                            <col style="width:123px;" />
                            <col style="width:auto;" />
                        </colgroup>
                        <tbody async_module="smartreview_dispListSearch">
                            <!--@import(/board/smartreview/provider/search.html)-->
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="reviewList ec-board-list-items-data {$*display_list_block_visible_class}">
                <ul async_module="smartreview_dispListItems">

                </ul>
            </div>
            <div class="reviewListEmpty ec-board-list-items-empty {$*display_list_empty_block_visible_class}">상품리뷰가 없습니다.</div>
            <button type="button" class="btnMore ec-board-list-more-button {$*display_list_more_button_visible_class}"><span>리뷰글 더 보기</span></button>
        </div>
        <!-- 비동기 실패시 노출되는 섹션 -->
        <div async_section="error" style="display:none;">
            <div class="reviewListEmpty">상품리뷰가 없습니다.</div>
        </div>
    </div>
    <!-- 비동기로 전달할 템플릿 영역(반드시 포함이 되어있어야함) -->
    <div class="ec-board-list-items-template" async_module="smartreview_dispListItems" async_type="template" style="display:none;">
        <ul async_section="after" style="display:none;">
            <!--@import(/board/smartreview/provider/square/item.html)-->
        </ul>
    </div>
</div>

</body>
</html>