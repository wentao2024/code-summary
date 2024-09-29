<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>Result Evaluation</title>
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

    /* navbar */
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
      /* 右边距，保持一定距离 */
    }

    button {
      padding: 8px 16px;
      margin-right: 10px;
      /* 确保按钮与下拉菜单之间有足够的空间 */
      background-color: #4CAF50;
      /* 或者任何其他希望的颜色 */
      color: white;
      border: none;
      cursor: pointer;
    }

    button:hover {
      background-color: #367c39;
      /* 鼠标悬停时的背景颜色变化 */
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

    .dropbtn::after {
      content: '▼';
      margin-left: 5px;
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
      /* 改变对齐方式为左对齐 */
      margin: 0;
      width: 80%;
      /* 可以调整宽度以适应不同屏幕尺寸 */
      max-width: 900px;
      /* 限制最大宽度，确保内容不会过宽 */
    }

    button {
      padding: 10px 20px;
      background-color: #1a1a1a;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      flex: none;
      /* 确保按钮不被拉伸或压缩 */
    }

    .footer {
      display: flex;
      justify-content: center;
      align-items: center;
      position: fixed;
      bottom: 20px;
      left: 0;
      right: 0;
      padding: 10px 0;
      background-color: #f4f4f4;
    }

    .custom-file-upload,
    #submit-btn {
      padding: 10px 20px;
      margin: 0 10px;
      background-color: #1a1a1a;
      color: white;
      border: none;
      cursor: pointer;
      border-radius: 4px;
    }

    #upload-feedback {
      margin-top: 10px;
      color: #1a1a1a;

    }

    .evaluation-container {
      max-width: 800px;
      margin: 0 auto;
      padding: 20px;
    }

    .code-summary-pair {
      border: 1px solid #ccc;
      padding: 10px;
      margin-bottom: 20px;
    }

    .rating-perspectives {
      display: flex;
      justify-content: space-around;
      margin-top: 10px;
    }

    .rating-perspective {
      display: flex;
      align-items: center;
    }

    #average-scores {
      margin-top: 20px;
    }
  </style>
</head>

<body>
<!-- navbar -->
<div class="navbar">
  <div class="left-items">
    <button onclick="location.href='history-management.html';">History</button>
    <div class="dropdown">
      <button class="dropbtn">Result Evaluator</button>
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


<!-- navbar -->
<script>
  // 简单的JavaScript来控制下拉菜单的显示
  document.querySelector('.dropbtn').addEventListener('click', function () {
    document.querySelector('.dropdown-content').style.display = 'block';
  });

  // 点击其他地方时关闭下拉菜单
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

<div class="main-content">
  <h2>How can I help you today?</h2>
  <p>
    I can help you with result evaluation.<br>
    Please upload your file in one of the following formats: DOC, DOCX, PDF, TXT, or ODT.
  </p>
</div>

<div class="evaluation-container" style="display: none">
  <h2>Evaluate Results</h2>
  <div class="code-summary-pair">
    <pre><code id="code-text"></code></pre>
    <p id="summarization"></p>
    <div class="rating-perspectives">
      <div class="rating-perspective">
        <label for="naturalness-rating">Naturalness:</label>
        <select id="naturalness-rating">
          <option value="5">Excellent</option>
          <option value="4">Good</option>
          <option value="3">Average</option>
          <option value="2">Bad</option>
          <option value="1">Poor</option>
        </select>
      </div>
      <div class="rating-perspective">
        <label for="usefulness-rating">Usefulness:</label>
        <select id="usefulness-rating">
          <option value="5">Excellent</option>
          <option value="4">Good</option>
          <option value="3">Average</option>
          <option value="2">Bad</option>
          <option value="1">Poor</option>
        </select>
      </div>
      <div class="rating-perspective">
        <label for="consistency-rating">Consistency:</label>
        <select id="consistency-rating">
          <option value="5">Excellent</option>
          <option value="4">Good</option>
          <option value="3">Average</option>
          <option value="2">Bad</option>
          <option value="1">Poor</option>
        </select>
      </div>
    </div>
    <textarea id="feedback" placeholder="Enter feedback (optional)"></textarea>
    <button id="save-evaluation">Save</button>
  </div>
  <button id="calculate-averages">Calculate Averages</button>
  <div id="average-scores"></div>
</div>
<div class="footer">
  <label for="file-upload" class="custom-file-upload">Click here to choose a file</label>
  <input id="file-upload" type="file" style="display: none;"/>
  <button id="submit-btn">Submit</button>
  <div id="upload-feedback"></div> <!-- 新增用于显示消息的元素 -->
</div>

<script>
  document.getElementById('file-upload').onchange = function () {
    if (this.files.length > 0) {
      var fileName = this.files[0].name;
      document.getElementById('upload-feedback').textContent = "File chosen: " + fileName;
    } else {
      document.getElementById('upload-feedback').textContent = "No file selected.";
    }
  };

  document.getElementById('submit-btn').onclick = function () {
    var fileInput = document.getElementById('file-upload');
    if (fileInput.files.length > 0) {
      var file = fileInput.files[0];
      document.getElementById('upload-feedback').textContent = "Uploading " + file.name + "...";
      var formData = new FormData();
      formData.append('file', file);

      fetch('/api/fileUpload', {
        method: 'POST',
        body: formData // Assuming you have the file data in formData
      })
      .then(response => response.json())
      .then(data => {
        document.querySelector('.main-content').remove();
        document.querySelector('.evaluation-container').style.display = 'block';

        update(data.generation)
      })

    } else {
      document.getElementById('upload-feedback').textContent = "Please select a file to upload.";
    }
  };

  <#noparse>

  function updateMainContentWithResult(fileName) {
    var mainContent = document.querySelector('.main-content');
    mainContent.innerHTML = `<h2>Result of the Uploaded File</h2><p>The file <strong>${fileName}</strong> has been successfully processed.</p>`;
  }

  // Assuming you have the file content as an array of objects with CodeText and Summarization properties

  function update(fileContent) {
    let currentIndex = 0;
    const codeTextElement = document.getElementById('code-text');
    const summarizationElement = document.getElementById('summarization');
    const calculateAveragesButton = document.getElementById('calculate-averages');
    const averageScoresElement = document.getElementById('average-scores');
    const saveEvaluationButton = document.getElementById('save-evaluation');
    const feedbackElement = document.getElementById('feedback');

    const ratingElements = {
      naturalness: document.getElementById('naturalness-rating'),
      usefulness: document.getElementById('usefulness-rating'),
      consistency: document.getElementById('consistency-rating')
    };

    const ratings = {
      naturalness: [],
      usefulness: [],
      consistency: []
    };
    function displayCodeSummaryPair() {
      const {CodeText, Summarization} = fileContent[currentIndex];
      codeTextElement.textContent = CodeText;
      summarizationElement.textContent = Summarization;
      feedbackElement.value = ''; // Clear feedback textarea
    }

    function calculateAverages() {
      const averages = {
        naturalness: ratings.naturalness.reduce((sum, rating) => sum + rating, 0) / ratings.naturalness.length,
        usefulness: ratings.usefulness.reduce((sum, rating) => sum + rating, 0) / ratings.usefulness.length,
        consistency: ratings.consistency.reduce((sum, rating) => sum + rating, 0) / ratings.consistency.length
      };

      averageScoresElement.innerHTML = `
    <h3>Average Scores</h3>
    <div>Naturalness: ${averages.naturalness.toFixed(2)}</div>
    <div>Usefulness: ${averages.usefulness.toFixed(2)}</div>
    <div>Consistency: ${averages.consistency.toFixed(2)}</div>
  `;
    }

    function saveEvaluation() {
      const {CodeText, Summarization} = fileContent[currentIndex];
      const feedback = feedbackElement.value;
      const naturalness = parseInt(ratingElements.naturalness.value);
      const usefulness = parseInt(ratingElements.usefulness.value);
      const consistency = parseInt(ratingElements.consistency.value);

      const data = {
        CodeText,
        Summarization,
        Feedback: feedback,
        Naturalness: naturalness,
        Usefulness: usefulness,
        Consistency: consistency
      };

      fetch('/api/resultEvaluation', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      })
      .then(response => response.json())
      .then(data => {
        console.log('Response from server:', data);
        if (data.success) {
          ratings.naturalness.push(naturalness);
          ratings.usefulness.push(usefulness);
          ratings.consistency.push(consistency);
          currentIndex++;
          alert('Save evaluation success.');

          if (currentIndex < fileContent.length) {
            displayCodeSummaryPair();
            calculateAveragesButton.addEventListener('click', calculateAverages);
          } else {
            saveEvaluationButton.remove();
          }
        } else {
          alert('Error saving evaluation: ' + data.message);
        }
      })
      .catch(error => {
        console.error('Error:', error);
        alert('An error occurred while saving the evaluation.');
      });
    }

    function recordRatings() {
      saveEvaluationButton.disabled = false;
    }

    displayCodeSummaryPair();

    ratingElements.naturalness.addEventListener('change', recordRatings);
    ratingElements.usefulness.addEventListener('change', recordRatings);
    ratingElements.consistency.addEventListener('change', recordRatings);
    saveEvaluationButton.addEventListener('click', saveEvaluation);
  }

  // update();

  </#noparse>

</script>

</body>

</html>