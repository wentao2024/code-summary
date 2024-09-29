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
            height: 35.5px;
        }

        .left-items {
            display: flex;
            align-items: center;
        }

        .right-items {
            margin-right: 30px;
            /* 右边距，保持一定距离 */
        }

        #logo {
            border-radius: 50%; 
            background-color: rgba(255, 255, 255, 0.5); 
            width: 25px; 
            height: 25px;
            margin-left: 10px;
            cursor: pointer;
        }

        #list {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
            text-align: center;
        }

        #list th {
            background-color: #333;
            color: white;
            padding: 10px 0;
        }

        #list td {
            padding: 15px 0;
            border-bottom: 1px solid #ddd;
        }

        button {
            padding: 8px 16px;
            /* margin-right: 10px; */
            /* 确保按钮与下拉菜单之间有足够的空间 */
            background-color: rgba(31, 31, 31, 0.5); 
            /* 或者任何其他希望的颜色 */
            color: white;
            border: none;
            cursor: pointer;
        }

        /* Popout Styles */
        .popout {
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
        }

        .popout-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 30%;
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
    <!-- navbar -->
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

    <h2 style="text-align: center; color: rgba(31, 31, 31, 0.8); padding: 15px;">User List</h2>
    <table id="list">
        <tr>
            <th>Username</th>
            <th>Role</th>
            <th>Operation</th>
        </tr>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td>${(user.role == 1)?then("Administrator", "User")}</td>
                <td>
                    <button onclick="showEditPrivilegePopout('${user.id}', '${(user.role == 1)?then("Administrator", "User")}')">Manage</button>
                    <input type="checkbox" class="user-selection" value="${user.id}">
                </td>
            </tr>
        </#list>
    </table>

    <div>
<#--        <h3>Selected Users:</h3>-->
<#--        <div id="selectedUsers"></div>-->
        <h3>Evaluation Data:</h3>
        <div id="evaluationData"></div>
        <button id="computeAverageScores">Compute Average Scores</button>
    </div>


    <!-- Edit Privilege Popout -->
    <div id="editPrivilegePopout" class="popout" style="display: none;">
        <div class="popout-content">
            <h3>Edit Privilege</h3>
            <label for="username" style="display: none;">Username:</label>
            <input type="text" id="username" style="display: none;" readonly>
            <label for="role">Role:</label>
            <div>
                <input type="radio" id="roleUser" name="role" value="User">
                <label for="roleUser">User</label>
                <input type="radio" id="roleAdmin" name="role" value="Administrator">
                <label for="roleAdmin">Administrator</label>
            </div>
            <button id="saveChanges">Save Changes</button>
        </div>
    </div>


    <#noparse>
    <!-- navbar -->
    <script>
        // Existing showEditPrivilegePopout function...

        const userSelectionCheckboxes = document.querySelectorAll('.user-selection');
        const selectedUsersContainer = document.getElementById('selectedUsers');
        const evaluationDataContainer = document.getElementById('evaluationData');

        userSelectionCheckboxes.forEach(checkbox => {
            // checkbox.addEventListener('change', updateSelectedUsers);
        });

        function updateSelectedUsers() {
            const selectedUsers = Array.from(userSelectionCheckboxes)
            .filter(checkbox => checkbox.checked)
            .map(checkbox => checkbox.value);

            selectedUsersContainer.innerHTML = '';
            selectedUsers.forEach(userId => {
                const userElement = document.createElement('span');
                userElement.textContent = `[${userId}], `;
                selectedUsersContainer.appendChild(userElement);
            });
        }

        const computeAverageScoresButton = document.getElementById('computeAverageScores');
        computeAverageScoresButton.addEventListener('click', fetchEvaluations);

        function fetchEvaluations() {
            const selectedUsers = Array.from(userSelectionCheckboxes)
            .filter(checkbox => checkbox.checked)
            .map(checkbox => checkbox.value);

            const requestParams = new URLSearchParams();
            requestParams.append('users', selectedUsers.join(','));

            fetch(`/api/evaluations?${requestParams.toString()}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    renderEvaluationData(data.evaluations);
                } else {
                    console.error('Failed to fetch evaluations');
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }

        function renderEvaluationData(evaluations) {
            evaluationDataContainer.innerHTML = '';
            const evaluationScores = {
                naturalness: [],
                usefulness: [],
                consistency: []
            };

            evaluations.forEach(evaluation => {
                const evaluationElement = document.createElement('div');
                evaluationElement.classList.add('evaluation-card');
                evaluationElement.innerHTML = `
            <div class="code-snippet">
                <h3>Code Snippet</h3>
                <pre><code>${evaluation.codeText}</code></pre>
            </div>
            <div class="evaluation-details">
                <h3>Summarization</h3>
                <p>${evaluation.summarization}</p>
                <div class="evaluation-scores">
                    <div class="score-card">
                        <h4>Naturalness</h4>
                        <p>${evaluation.evaluations.naturalnessscore}</p>
                    </div>
                    <div class="score-card">
                        <h4>Usefulness</h4>
                        <p>${evaluation.evaluations.usefulnessscore}</p>
                    </div>
                    <div class="score-card">
                        <h4>Consistency</h4>
                        <p>${evaluation.evaluations.consistencyscore}</p>
                    </div>
                </div>
            </div>
        `;
                evaluationDataContainer.appendChild(evaluationElement);

                // Store scores for average calculation
                evaluationScores.naturalness.push(evaluation.evaluations.naturalnessscore);
                evaluationScores.usefulness.push(evaluation.evaluations.usefulnessscore);
                evaluationScores.consistency.push(evaluation.evaluations.consistencyscore);
            });

            // Calculate and display average scores
            const averageScoresContainer = document.createElement('div');
            averageScoresContainer.classList.add('average-scores');
            averageScoresContainer.innerHTML = `
        <h2>Average Scores</h2>
        <div class="score-card">
            <h4>Naturalness</h4>
            <p>${calculateAverage(evaluationScores.naturalness)}</p>
        </div>
        <div class="score-card">
            <h4>Usefulness</h4>
            <p>${calculateAverage(evaluationScores.usefulness)}</p>
        </div>
        <div class="score-card">
            <h4>Consistency</h4>
            <p>${calculateAverage(evaluationScores.consistency)}</p>
        </div>
    `;
            evaluationDataContainer.appendChild(averageScoresContainer);
        }

        function calculateAverage(scores) {
            if (scores.length === 0) return 0;
            const sum = scores.reduce((acc, curr) => acc + curr, 0);
            return (sum / scores.length).toFixed(2);
        }



        function showEditPrivilegePopout(username, role) {
            const popout = document.getElementById('editPrivilegePopout');
            const usernameInput = document.getElementById('username');
            const roleRadios = document.querySelectorAll('input[name="role"]');

            usernameInput.value = username;
            roleRadios.forEach(radio => {
                if (radio.value === role) {
                    radio.checked = true;
                } else {
                    radio.checked = false;
                }
            });

            popout.style.display = 'block';

            const saveChangesButton = document.getElementById('saveChanges');
            saveChangesButton.addEventListener('click', () => {
                const selectedRole = document.querySelector('input[name="role"]:checked').value;
                const requestData = {
                    id: username, // Assuming the username is the user ID
                    role: selectedRole === 'Administrator' ? 1 : 0
                };

                fetch('/api/userManagement/editPrivilege', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(requestData)
                })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        console.log(data.message);
                        popout.style.display = 'none';
                        window.location.reload();
                    } else {
                        console.error('Failed to update user privileges');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                });
            });
        }

        window.onclick = function(event) {
            const popout = document.getElementById('editPrivilegePopout');
            if (event.target === popout) {
                popout.style.display = 'none';
            }
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
    </#noparse>
</body>

</html>