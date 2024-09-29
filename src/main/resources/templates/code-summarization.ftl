<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>Code Summarization</title>
  <style>
    body,
    html {
      margin: 0;
      padding: 0;
      height: 100%;
      font-family: Arial, sans-serif;
      color: #333;
      background-color: #f4f4f4;
    }

    .navbar {
      display: flex;
      justify-content: space-between;
      align-items: center;
      background-color: #333;
      color: white;
      padding: 10px;
      width: 100%;
    }

    .left-items {
      display: flex;
      align-items: center;
    }

    .right-items {
      margin-right: 30px;
    }

    button {
      padding: 8px 16px;
      margin-right: 10px;
      background-color: #4CAF50;
      color: white;
      border: none;
      cursor: pointer;
    }

    button:hover {
      background-color: #367c39;
    }

    .dropdown {
      position: relative;
      display: inline-block;
    }

    .dropdown-content {
      display: none;
      position: absolute;
      background-color: #f9f9f9;
      min-width: 160px;
      box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
      z-index: 1;
    }

    .dropdown-content a {
      color: black;
      padding: 12px 16px;
      text-decoration: none;
      display: block;
    }

    .dropdown-content a:hover {
      background-color: #f1f1f1;
    }

    .dropdown:hover .dropdown-content {
      display: block;
    }

    .main-content {
      position: absolute;
      top: 20%;
      left: 10%;
      /* transform: translate(-50%, -50%); */
      padding: 20px;
      text-align: left;
      margin: 0;
      width: 80%;
      max-width: 900px;
    }

    .main-content h2,
    .main-content p {
      margin-left: 10px;
      /* 增加左边距以美化布局 */
    }

    .input-area {
      display: flex;
      align-items: center;
      position: fixed;
      bottom: 20px;
      left: 150px;
      right: 150px;
      padding: 10px;
      background-color: #f4f4f4;
      box-sizing: border-box;
    }

    .number-select {
      margin-right: 10px;
      padding: 5px 10px;
      border: 1px solid #ccc;
      border-radius: 4px;
      flex: none;
    }

    input[type="text"] {
      flex-grow: 1;
      padding: 8px;
      margin: 0;
      border: 1px solid #ccc;
      border-radius: 4px;
    }

    button {
      padding: 10px 20px;
      background-color: #1a1a1a;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      flex: none;
    }
  </style>
</head>

<body>
<div class="navbar">
  <div class="left-items">
    <button onclick="location.href='history-management.html';">History</button>
    <div class="dropdown">
      <button class="dropbtn">Code Summarizer</button>
      <div class="dropdown-content">
        <a href="code-summarization">Code Summarizer</a>
        <a href="result-evaluation">Result Evaluator</a>
      </div>
    </div>
  </div>
  <div class="right-items">
    <span id="welcomeMessage" style="padding-right: 42px;">Welcome, <span id="username">${user.username}</span></span>
    <a href="manage"><img src="manage.png" alt="user manage"
                               style="position: absolute; right: 16px; top: 14px; height: 25px;"></a>
  </div>
</div>

<div class="main-content">
  <div id="intro-section">
    <h2>How can I help you today?</h2>
    <p id="intro-text">I can help you with code summarization for any language, such as Java, Python, C++, and
      more. Just submit your code, and I'll provide a concise summary!</p>
  </div>
  <div id="summary-result"></div> <!-- Placeholder for the summary -->
  <div id="rating-feedback"></div> <!-- 用于显示评分提交后的反馈信息 -->

</div>

<div class="input-area">
  <select class="number-select">
    <option value="1">1</option>
    <option value="2">2</option>
    <option value="3">3</option>
    <option value="4">4</option>
    <option value="5">5</option>
    <option value="6">6</option>
    <option value="7">7</option>
    <option value="8">8</option>
    <option value="9">9</option>
    <option value="10">10</option>
  </select>
  <input type="text" id="codeInput" placeholder="Enter your code here">
  <button onclick="submitCode()">Submit</button>
</div>

<script>
  // Assuming the username is stored in local storage
  document.getElementById('username').textContent = '${user.username}' || 'Guest';

  function submitCode() {
    var code = document.getElementById('codeInput').value;
    var num = document.querySelector('.number-select').value;
    var contentArea = document.getElementById('summary-result');
    var introSection = document.getElementById('intro-section');

    if (code.trim() === '') {
      contentArea.innerHTML = '<p>Please enter some code to summarize.</p>';
    } else {
      // Make a POST request to the codeSummarization API
      fetch('/api/codeSummarization', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          codeInput: code,
          numberOfResults: num
        })
      })
      .then(response => response.json())
      .then(data => {
        if (data.success) {
          // Display the summarizations and rating selectors in the content area
          var summaryHTML = '<h2>Summarizations</h2>';
          data.summarizations.forEach((summary, index) => {
            summaryHTML += '<div class="summary-item">';
            summaryHTML += '<p>' + summary + '</p>';
            summaryHTML += '<div class="rating-section">';
            summaryHTML += '<label>Accuracy:</label>';
            summaryHTML += '<select id="accuracy-rating-' + index + '">';
            summaryHTML += '<option value="1">1</option>';
            summaryHTML += '<option value="2">2</option>';
            summaryHTML += '<option value="3">3</option>';
            summaryHTML += '<option value="4">4</option>';
            summaryHTML += '<option value="5">5</option>';
            summaryHTML += '</select>';
            summaryHTML += '<label>Clarity:</label>';
            summaryHTML += '<select id="clarity-rating-' + index + '">';
            summaryHTML += '<option value="1">1</option>';
            summaryHTML += '<option value="2">2</option>';
            summaryHTML += '<option value="3">3</option>';
            summaryHTML += '<option value="4">4</option>';
            summaryHTML += '<option value="5">5</option>';
            summaryHTML += '</select>';
            summaryHTML += '<label>Relevance:</label>';
            summaryHTML += '<select id="relevance-rating-' + index + '">';
            summaryHTML += '<option value="1">1</option>';
            summaryHTML += '<option value="2">2</option>';
            summaryHTML += '<option value="3">3</option>';
            summaryHTML += '<option value="4">4</option>';
            summaryHTML += '<option value="5">5</option>';
            summaryHTML += '</select>';
            summaryHTML += '<button class="rating-submit" onclick="submitRatings(' + index + ')">Submit Ratings</button>';
            summaryHTML += '</div>';
            summaryHTML += '</div>';
          });
          contentArea.innerHTML = summaryHTML;

          // Hide the intro section
          introSection.style.display = 'none';
        } else {
          // Handle error response
          contentArea.innerHTML = '<p>Failed to summarize code. Please try again later.</p>';
        }
      })
      .catch(error => {
        console.error('Error:', error);
        contentArea.innerHTML = '<p>An error occurred while processing your request. Please try again later.</p>';
      });
    }
  }

  function submitRatings(index) {
    var accuracy = document.getElementById('accuracy-rating-' + index).value;
    var clarity = document.getElementById('clarity-rating-' + index).value;
    var relevance = document.getElementById('relevance-rating-' + index).value;
    var code = document.getElementById('codeInput').value;
    var contentArea = document.getElementById('summary-result');

    // Create an object with the rating data
    var ratingData = {
      CodeText: code, // Replace 'code' with the actual code to be summarized
      Summarization: document.querySelector('.summary-item p').textContent,
      Feedback: '', // Add the feedback message from the user if needed
      Naturalness: parseInt(accuracy),
      Usefulness: parseInt(clarity),
      Consistency: parseInt(relevance)
    };

    // Make a POST request to the resultEvaluation API
    fetch('/api/resultEvaluation', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(ratingData)
    })
    .then(response => response.json())
    .then(data => {
      if (data.success) {
        // Update the feedback area with the success message
        contentArea.innerHTML = 'Ratings submitted successfully. Accuracy: ' + accuracy + ', Clarity: ' + clarity
            + ', Relevance: ' + relevance;
      } else {
        // Handle error response
        console.error('Evaluation submission failed:', data.message);
        contentArea.innerHTML = 'Failed to submit ratings. Please try again later.';
      }
    })
    .catch(error => {
      console.error('Error:', error);
      contentArea.innerHTML = 'An error occurred while processing your request. Please try again later.';
    });
  }

  // Close dropdown menu if the user clicks outside of it
  window.onclick = function (event) {
    if (!event.target.matches('.dropbtn')) {
      var dropdowns = document.getElementsByClassName("dropdown-content");
      for (var i = 0; i < dropdowns.length; i++) {
        var openDropdown = dropdowns[i];
        if (openDropdown.style.display === 'block') {
          openDropdown.style.display = 'none';
        }
      }
    }
  }
</script>

</body>

</html>