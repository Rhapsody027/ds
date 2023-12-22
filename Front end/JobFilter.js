// JobFilter.js

import React, { useState } from 'react';
import './JobFilter.css';

const JobFilter = () => {
  const [selectedOptions, setSelectedOptions] = useState({
    education: '',
    shift: '',
    salaryRange: '',
    field: '',
  });

  const [selectedResults, setSelectedResults] = useState([]);

  const handleChange = (e, category) => {
    setSelectedOptions({
      ...selectedOptions,
      [category]: e.target.value,
    });
  };

// 在 React 前端中，使用 Fetch 或 Axios 等工具
const handleSubmit = async () => {
  const resultsArray = Object.values(selectedOptions).filter(value => value !== '');

  try {
    const response = await fetch('http://your-java-api-endpoint', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ results: resultsArray }),
    });

    if (response.ok) {
      // 處理後端回應
      const data = await response.json();
      console.log('後端回應:', data);
    } else {
      console.error('HTTP錯誤:', response.status);
    }
  } catch (error) {
    console.error('發生錯誤:', error);
  }
};


  return (
    <div className="container">
      <div className="dropdown">
        <label>學歷: </label>
        <select onChange={(e) => handleChange(e, 'education')}>
          <option value="高中職">高中職</option>
          <option value="大學">大學</option>
          <option value="碩士">碩士</option>
          <option value="博士">博士</option>
        </select>
      </div>

      <div className="dropdown">
        <label>時段: </label>
        <select onChange={(e) => handleChange(e, 'shift')}>
          <option value="早班">早班</option>
          <option value="晚班">晚班</option>
          <option value="零工">零工</option>
        </select>
      </div>

      <div className="dropdown">
        <label>薪水範圍: </label>
        <select onChange={(e) => handleChange(e, 'salaryRange')}>
          <option value="35000以下">35000以下</option>
          <option value="35000~50000">35000~50000</option>
          <option value="50000~75000">50000~75000</option>
          <option value="75000以上">75000以上</option>
        </select>
      </div>

      <div className="dropdown">
        <label>領域: </label>
        <select onChange={(e) => handleChange(e, 'field')}>
          <option value="資訊">資訊</option>
          <option value="醫療">醫療</option>
          <option value="文書">文書</option>
          <option value="服務業">服務業</option>
        </select>
      </div>

      <button onClick={handleSubmit}>提交</button>

      {/* 顯示選取的結果 */}
      <div>
        <h3>選取的結果:</h3>
        <ul>
          {selectedResults.map((result, index) => (
            <li key={index}>{result}</li>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default JobFilter;
