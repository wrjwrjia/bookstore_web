<%--
  Created by IntelliJ IDEA.
  User: jia
  Date: 3/27/17
  Time: 1:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Base</title>
    <script src="js/jQuery.js"></script>
    <link href="css/base.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div class = "header">
    <div class = 'logo' style="float: left">DB Bookstore</div>
        <div class="searchform">
            <select id="view">
                <option value="0" selected>Please select </option>
                <option value="1">ISBN </option>
                <option value="2">Book Name </option>
                <option value="3">Author  </option>
            </select>
            <div class="input">
            <input type="text" id="input" maxlength="100">
        </div>
        </div>
        <div class='submit'>
            <button class="submit_btn" onclick="submit()">Search</button>
        </div>
    </div>
    <div id="table_div"></div>
</body>
</html>

<script>
    function submit(){
        var option = document.getElementById("view");//get the selected option
        var value = option.value;
        var input = $('#input').val();
        $.ajax({
            type:"post",//request
            url:"controller/searchController.jsp",
            data:{"value":value,"input":input},//pass varianble to controller
            error:function(){
                alert("Failed submission!");
            },
            success:function(data){ //success
                if(data == -1){
                    alert('Search cannot be null!');
                }else if(data == -2) {
                    alert('No result!');
                }
                else
                {
                    /**var table = new TableView('table_div');
                    table.header = {
                        addedDate   : 'Added Date',
                        ISBN		: 'ISBN',
                        major		: 'Major',
                        author		: 'Author',
                        name        : 'Book Name',
                        location    : 'Location'
                    };
                    table.dataKey = 'ISBN';
                    table.add({);
                    table.render();**/
                    var htmlStr = "<table>";
                    for(var i = 0; i < data.length; i++){
                        var list = data[0];
                        htmlStr += "<tr><td>"+list.addedDate+"</td><td>"+list.ISBN+"</td></tr>";
                    }
                    htmlStr += "</table>";
                    $('logo').hmtl(htmlStr);
                }
            }
        });
    }
</script>