import React from 'react';
import * as d3 from "d3";
class WikiDashBoard extends React.Component {

    constructor(props) {
      super(props);
      this.state = {
        //states
     };
      
    }

    componentDidUpdate(prevProps,prevState){
        let potentialBrandReturn  = this.props.datas === undefined;
        if(!potentialBrandReturn && !this.state.isDataPopulated) {
          this.processData(this.props);
          this.setState({isDataPopulated : true})
        } else {
         return false;
        }
     }
  processData(props) {
    
        const { datas } = props;
        var margin = {top: 20, right: 20, bottom: 30, left: 50},
            width = 960 - margin.left - margin.right,
            height = 500 - margin.top - margin.bottom;

        var parseTime = d3.timeParse("%a %b %d %Y %H:%M:%S GMT+0530 (India Standard Time)");

        // set the ranges
        var x = d3.scaleTime().range([0, width]);
        var y = d3.scaleLinear().range([height, 0]);

        // define the line
        var valueline = d3.line()
            .x(function(d) { return x(d.requestedTime); })
            .y(function(d) { return y(d.wikiResponseTime); });

        var svg = d3.select("a").append("svg")
            .attr("width", width + margin.left + margin.right)
            .attr("height", height + margin.top + margin.bottom)
            .append("g")
            .attr("transform",
            "translate(" + margin.left + "," + margin.top + ")");


        // format the data
        datas.forEach((d) => {
            d.requestedTime = parseTime(d.requestedTime);
            d.wikiResponseTime = +d.wikiResponseTime;
        });

        // Scale the range of the data
        x.domain(d3.extent(datas, function(d) { return d.requestedTime; }));
        y.domain([0, d3.max(datas, function(d) { return d.wikiResponseTime; })]);

        // Add the valueline path.
        svg.append("path")
            .data([datas])
            .attr("class", "line")
            .attr("d", valueline);

        // Add the X Axis
        svg.append("g")
            .attr("transform", "translate(0," + height + ")")
            .call(d3.axisBottom(x));

        // Add the Y Axis
        svg.append("g")
            .call(d3.axisLeft(y));
    }

    render() {
       
        return <a> </a>
}

    
}

export default WikiDashBoard;
      