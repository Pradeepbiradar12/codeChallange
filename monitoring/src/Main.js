import React from 'react';
import axios from 'axios';
import ReactTable from 'react-table'
import GitHub from 'github-api';

import 'react-table/react-table.css'
import DashBoard from './D3/DashBoard';
import WikiDashBoard from './D3/WikiDashBoard';

class Main extends React.Component {
 
  constructor(props) {
    super(props);
    this.state = {
      response : {},
      allResponseTime: []
    } 
    var url='http://localhost:8080/api/v1/get-response';
    
    axios.get(url, {
      headers: {
        'Access-Control-Allow-Origin': '*',
      }
    }).then((res) => {
        this.setState({
          response : res.data
        })
        if(res.data.wikiResponseTime > 200) {
            this.postNotification("Wiki reponse time is grater then 200 milliseconds");
            this.createGitIssue("Wiki reponse time is grater then 200 milliseconds");

        }
        if(res.data.intuitResponseTime > 200) {
          this.postNotification("Intuit reponse time is grater then 200 milliseconds");
          this.createGitIssue("Intuit reponse time is grater then 200 milliseconds");
      }
    }).catch(function (res) {
      console.log(res)
    })
    axios.get("http://localhost:8080/api/v1/get-all-response", {
      headers: {
        'Access-Control-Allow-Origin': '*',
      }
    }).then((res) => {
        this.setState({
          allResponseTime : res.data
        })
    }).catch(function (res) {
      console.log(res)
    });


  }

  createGitIssue(message) {
    const url = 'https://api.github.com/repos/Pradeepbiradar12/codeChallange/issues'
    const data = {
      "title": "Found a bug",
      "body": message,
      "assignees": [
        "pradeepbiradar12"
      ],
      "labels": [
        "issue"
      ]
    }
    axios.post(url, JSON.stringify(data),{ headers: {
      'Authorization': 'token be0efa0005ab61bf8856f96b10da15609cda81d9',
    }}, {
      withCredentials: false,
      transformRequest: [(data, headers) => {
      delete headers.post["Content-Type"]
      return data
    }]
  })
  }

  postNotification(message) {
    const url = 'https://hooks.slack.com/services/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'
    const data = {
      "text": message,
    }
    axios.post(url, JSON.stringify(data), {
      withCredentials: false,
      transformRequest: [(data, headers) => {
      delete headers.post["Content-Type"]
      return data
    }]
  })
}


    render() {
      const columns = [{
        Header: 'WikiResponseTime',
        accessor: 'wikiResponseTime'
      }, {
        Header: 'IntuitResponseTime',
        accessor: 'intuitResponseTime',
      },
      {
        Header: 'requested for response Time ',
        accessor: 'requestedTime'
      }]
    
      return (
        <div>
         Wiki website response time:  {this.state.response.wikiResponseTime},
         Intuit website response time:  {this.state.response.intuitResponseTime}
         <div>
         {/* <ReactTable
          data={this.state.allResponseTime}
          columns={columns}
        /> */}

         <div>
        <DashBoard datas={this.state.allResponseTime}></DashBoard>
        <WikiDashBoard datas={this.state.allResponseTime}></WikiDashBoard>
        </div>
        </div>

       
      </div>
      )
    }
  }

  export default Main;