<script type="text/javascript">
<!--
var speed = 10;
var pause = 1500;
var timerID = null;
var bannerRunning = false;

var ar = new Array();

ar[0] = "Welcome to WYSIWYG Web Builder!";
ar[1] = "The easiest Web Page generator available";
ar[2] = "http://www.wysiwygwebbuilder.com";

var message = 0;
var state = "";

clearState();

function stopBanner()
{
   if (bannerRunning)
      clearTimeout(timerID);
   timerRunning = false;
}

function startBanner()
{
   stopBanner();
   showBanner();
}

function clearState()
{
   state = "";
   for (var i = 0; i < ar[message].length; ++i)
   {
      state += "0";
   }
}

function showBanner()
{
   if (getString())
   {
      message++;
      if (ar.length <= message)
         message = 0;
      clearState();
      timerID = setTimeout("showBanner()", pause);
   }
   else
   {
      var str = "";
      for (var j = 0; j < state.length; ++j)
      {
         str += (state.charAt(j) == "1") ? ar[message].charAt(j) : "     ";
      }
      window.status = str;
      timerID = setTimeout("showBanner()", speed);
   }
}

function getString()
{
   var full = true;
   for (var j = 0; j < state.length; ++j)
   {
      if (state.charAt(j) == 0)
         full = false;
   }
   if (full) return true;
   while (1)
   {
      var num = getRandom(ar[message].length);
      if (state.charAt(num) == "0")
         break;
   }
   state = state.substring(0, num) + "1" + state.substring(num + 1, state.length);
   return false;
}

function getRandom(max)
{
   var now = new Date();
   var num = now.getTime() * now.getSeconds() * Math.random();
   return num % max;
}

startBanner();
// -->
