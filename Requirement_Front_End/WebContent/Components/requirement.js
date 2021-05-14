$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
	 $("#alertSuccess").hide(); 
 } 
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
var status = validateRequirementForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidreqIdSave").val() == "") ? "POST" : "PUT";

$.ajax( 
{ 
url : "requirementAPI", 
type : type, 
data : $("#formRequirement").serialize(), 
dataType : "text", 
complete : function(response, status) 
{ 
onItemSaveComplete(response.responseText, status); 
} 
});

});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidreqIdSave").val($(this).data("reqId"));
 $("#resId").val($(this).closest("tr").find('td:eq(0)').text());
 $("#reqType").val($(this).closest("tr").find('td:eq(1)').text());
 $("#reqNote").val($(this).closest("tr").find('td:eq(2)').text());
});
//DELETE====================================================
$(document).on("click", ".btnRemove", function(event)
{ 

 $.ajax( 
 { 
 url : "requirementAPI", 
 type : "DELETE", 
 data : "reqId=" + $(this).data("reqId"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemDeleteComplete(response.responseText, status); 
 } 

 }); 
});
// CLIENT-MODEL================================================================
function validateRequirementForm()
{
if ($("#resId").val().trim() == "")
 {
 return "Insert ResId.";
 }
if ($("#reqType").val().trim() == "")
 {
 return "Insert Req_Type.";
 }
if ($("#reqNote").val().trim() == "")
 {
 return "Insert Req_Note.";
 }
// is numerical value
var tmpResId = $("#resId").val().trim();
if (!$.isNumeric(tmpResId))
 {
 return "Insert a numerical value for ResId.";
 }
return true;
}
function onItemSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 

 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved In Requirement Table."); 
 $("#alertSuccess").show();
 $("#divRequirementGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error..! While Saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
 $("#hidreqIdSave").val(""); 
 $("#formRequirement")[0].reset(); 
}
function onItemDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 

 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show();
 $("#divRequirementGrid").html(resultSet.data); 
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