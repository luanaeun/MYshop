<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 <link href="css/inc_top.css" rel="stylesheet" type="text/css">

<div class="header-bottom">
	<div>
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
	</div>

	<div class="mainmenu pull-left">
		<ul class="nav navbar-nav collapse navbar-collapse">
			<li><a href="index.html" class="active">Home</a></li>
			<li class="dropdown"><a href="#">Shop<i
					class="fa fa-angle-down"></i></a>
				<ul role="menu" class="sub-menu">
					<li><a href="shop.html">Products</a></li>
					<li><a href="product-details.html">Product Details</a></li>
					<li><a href="checkout.html">Checkout</a></li>
					<li><a href="cart.html">Cart</a></li>
					<li><a href="login.html">Login</a></li>
				</ul></li>
			<li class="dropdown"><a href="#">Blog<i
					class="fa fa-angle-down"></i></a>
				<ul role="menu" class="sub-menu">
					<li><a href="blog.html">Blog List</a></li>
					<li><a href="blog-single.html">Blog Single</a></li>
				</ul></li>
			<li><a href="404.html">404</a></li>
			<li><a href="contact-us.html">Contact</a></li>
		</ul>
	</div>


	<div class="col-sm-3">
		<div class="search_box pull-right">
			<input type="text" placeholder="Search" />
		</div>
	</div>
	
</div>

<!--/header-bottom-->