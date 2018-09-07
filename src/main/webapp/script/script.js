$(document).ready(function() {
 setTimeout(function(){ 
        var x = document.getElementById("snackbar");
        x.className = "show";
        setTimeout(function(){ x.className = x.className.replace("show", ""); }, 1000);
       }, 2000)
    });
window.onload = function(e){ 
   var id = document.getElementById("pager-id").value; 
   if (id <= 1) {
      var d = document.getElementById("previous");
      d.className += " disabled";
      document.getElementById('previous-link').removeAttribute('href');
   }
}

$(document).ready(function() {
    $('.assign-table tr').click(function(event) {
        if (event.target.type !== 'checkbox') {
            $(':checkbox', this).trigger('click');
        }
    });
});

$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
       if($.isNumeric(value)) {
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).find("td:first").text().toLowerCase().indexOf(value) > -1)
    }); } else {
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
    } });
});

$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
       if($.isNumeric(value)) {
    $("#myTable1 tr").filter(function() {
      $(this).toggle($(this).find("td:first").text().toLowerCase().indexOf(value) > -1)
    }); } else {
    $("#myTable1 tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
    } });
});

$("#choice").change(function(){
    var choice = $(this).val().toUpperCase();
    $("table tr").each(function (index) {
                if (index !== 0) {
                    $row = $(this);
                    var id = $row.find("td.type").text().toUpperCase();
                    if (id.indexOf(choice) == -1) {
                        $row.hide();
                    }
                    else {
                        $row.show();
                    }
                }
            });
});

function validate(){
  var checkboxes = document.querySelectorAll('input[type="checkbox"]');
  var checkedOne = Array.prototype.slice.call(checkboxes).some(x => x.checked);
  if(checkedOne) {
    document.getElementById("assign-form").submit();
  } else {
    alert("Select Atleast one Entry!");
  }
}
function setResources() {
  document.getElementById("resources").value = $(":checkbox:checked").length;
}

    $(document).ready(function () {
     document.getElementById("age").value = GetAge();
        function onchange() {
            var birthDate = $('#birthDate');
            var age = $('#age');
            age.val(GetAge(birthDate.val()));
        }
        $('#birthDate').on('change', onchange);
    });

function GetAge() {
    var today = new Date();
    var birthDate = new Date(document.getElementById('birthDate').value);
    var age = today.getFullYear() - birthDate.getFullYear();
    var m = today.getMonth() - birthDate.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
        age--;
    }
    return age;
}

$('.ml6 .letters').each(function(){
  $(this).html($(this).text().replace(/([^\x00-\x80]|\w)/g, "<span class='letter'>$&</span>"));
});

anime.timeline({loop: false})
  .add({
    targets: '.ml6 .letter',
    translateY: ["1.1em", 0],
    translateZ: 0,
    duration: 750,
    delay: function(el, i) {
      return 50 * i;
    }
  }).add({
    targets: '.ml6',
    opacity: 1,
    duration: 1000,
    easing: "easeOutExpo",
    delay: 1000
  });
