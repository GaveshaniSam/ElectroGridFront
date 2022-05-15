$(document).ready(function()
{ 
 $("#alertSuccess").hide();
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 

// Form validation-------------------
var status = validateTransmissionForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 

// If valid------------------------
var type = ($("#hidTransmissionIDSave").val() == "") ? "POST" : "PUT";
 		
 		$.ajax(
		{
			 url : "TransmissionAPI",
 			 type : type,
 			 data : $("#formTransmission").serialize(),
 			 dataType : "text",
 			 complete : function(response, status)
 				{
 					onTransmissionSaveComplete(response.responseText, status);
 				}
 	});
 	
 });
 	
 	function onTransmissionSaveComplete(response, status)
	{
		if (status == "success")
 		{
 			var resultSet = JSON.parse(response);
 			
 		if (resultSet.status.trim() == "success")
 		{
 			$("#alertSuccess").text("Successfully saved.");
 			$("#alertSuccess").show();
 			$("#divTransmissionGrid").html(resultSet.data);
 		} else if (resultSet.status.trim() == "error")
 			{
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
 			}
 		} else if (status == "error")
 			{
 				$("#alertError").text("Error while saving.");
 				$("#alertError").show();
 		} else
 			{
 				$("#alertError").text("Unknown error while saving..");
 				$("#alertError").show();
 		}
 		$("#hidTransmissionIDSave").val("");
 		$("#formTransmission")[0].reset();
}
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
        $("#hidTransmissionIDSave").val($(this).data("transid"));
 		$("#no").val($(this).closest("tr").find('td:eq(0)').text());
 		$("#area").val($(this).closest("tr").find('td:eq(1)').text());
	    $("#name").val($(this).closest("tr").find('td:eq(2)').text());
 		$("#voltage").val($(this).closest("tr").find('td:eq(3)').text());
 		$("#date").val($(this).closest("tr").find('td:eq(4)').text());
 		$("#time").val($(this).closest("tr").find('td:eq(5)').text());
}); 

//DELETE==========================================
$(document).on("click", ".btnRemove", function(event)
{
 		$.ajax(
 		{
 			url : "TransmissionAPI",
 			type : "DELETE",
 			data : "transID=" + $(this).data("transid"),
 			dataType : "text",
 			complete : function(response, status)
 	    	{
 				onTransmissionDeleteComplete(response.responseText, status);
 			}
 	  });
});

function onTransmissionDeleteComplete(response, status)
{
	if (status == "success")
 	{
 		var resultSet = JSON.parse(response);
 		if (resultSet.status.trim() == "success")
 		{
 			$("#alertSuccess").text("Successfully deleted.");
 			$("#alertSuccess").show();
 			$("#divTransmissionGrid").html(resultSet.data);
 			
 		} else if (resultSet.status.trim() == "error")
 		{
 			$("#alertError").text(resultSet.data);
 			$("#alertError").show();
 		}
    } else if (status == "error")
 		{
 			$("#alertError").text("Error while deleting.");
 			$("#alertError").show();
 	} else
 	{
 		$("#alertError").text("Unknown error while deleting..");
 		$("#alertError").show();
 	}
}

// CLIENT-MODEL================================================================
function validateTransmissionForm() 
{ 
// Transmission No
if ($("#no").val().trim() == "") 
 { 
 return "Insert Transmission No."; 
 } 
// Transmission Area
if ($("#area").val().trim() == "") 
 { 
 return "Insert Transmission Area."; 
 } 
 
// Transmission Name
if ($("#name").val().trim() == "") 
 { 
 return "Insert Transmission Name."; 
 } 

// Transmission Voltage
if ($("#voltage").val().trim() == "") 
 { 
 return "Insert Transmission Voltage."; 
 } 

// Transmission Date
if ($("#date").val().trim() == "") 
 { 
 return "Insert Transmission Date."; 
 } 

// Transmission Time
if ($("#time").val().trim() == "") 
 { 
 return "Insert Transmission Time."; 
 } 

return true; 
}
