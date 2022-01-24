<template>
  <div id="svg" @onload="drawSvg"></div>
</template>

<script>
import { SVG, extend as SVGextend, Element as SVGElement } from '@svgdotjs/svg.js'

const WIDTH = 400;
const HEIGHT = 400;
const X_CENTER = 0;
const Y_CENTER = 0;

let CANVAS = null;

const BACKGROUND_COLOR = '#fff';
const AXES_COLOR = '#000'
const AREA_COLOR = '#AF412D';

const pointsScale = 5;
let scale = 0.035;

function convertX(x) {
  return (WIDTH / 2 + x / scale + X_CENTER / scale);
}

function convertY(y) {
  return (HEIGHT / 2 - y / scale - Y_CENTER / scale)
}

function convertToCoordinatesX(xPoint) {
  return (xPoint - WIDTH / 2) * scale - X_CENTER;
}

function convertToCoordinatesY(yPoint) {
  return (HEIGHT / 2 - yPoint) * scale - Y_CENTER;
}

export default {
  name: "SvgArea",

  props: [ 'currentR', 'list'],

  watch: {
    list(val) {
      console.log("Массив точек изменился: \"" + JSON.stringify(val) + "\"");
      console.log(val === this.$props.list)
      this.drawSvg()
    }
  },

  methods: {
    drawSvg() {
      document.getElementById('svg').innerHTML = '' // очистка
      console.log("Полученный массив точек: \"" + JSON.stringify(this.$props.list) + "\"");
      CANVAS = SVG()
        .addTo('#svg')
        .size('100%', '100%')
        .viewbox(0, 0, WIDTH, HEIGHT);
      if (this.$props.list === undefined || this.$props.list.length === 0) {
        this.initSvg();
      } else {
        this.drawPlotWithPoints();
      }
    },

    initSvg() {
      console.log('Init plot!');
      CANVAS.rect(WIDTH, HEIGHT).fill(BACKGROUND_COLOR);
      this.drawArea();
      this.drawAxes();
      this.drawAxesScaleLabels();
      this.drawRValue();
    },

    drawPlotWithPoints() {
      console.log('Ready to draw plot!');
      this.drawArea();
      this.drawAxes();
      this.drawAxesScaleLabels();
      for (let i = 0; i < this.$props.list.length; i++) {
        let point = this.$props.list[i];
        this.drawPoint(point.x, point.y, point.hit, pointsScale);
      }
      this.drawRValue();
    },

    drawAxes() {
      const arrowSize = 10
      //axis x
      CANVAS.line(0, (HEIGHT / 2), WIDTH, (HEIGHT / 2)).stroke({width: 2, color: AXES_COLOR});
      //axis arrow
      const triangleX = (WIDTH - arrowSize) + ',' + (HEIGHT / 2 - arrowSize / 2) + ' ' +
        (WIDTH - arrowSize) + ',' + (HEIGHT / 2 + arrowSize / 2) + ' ' +
        (WIDTH) + ',' + (HEIGHT / 2)
      console.log('x arrow coordinates ' + triangleX)
      CANVAS.polygon(triangleX).fill(AXES_COLOR)
      CANVAS.text('x').font({
        size: 16,
        family: 'Menlo, sans-serif',
        anchor: 'end',
        fill: AXES_COLOR
      }).move(WIDTH - 2 * arrowSize, HEIGHT / 2 - 2 * arrowSize)
      //axis y
      CANVAS.line(WIDTH / 2, 0, WIDTH / 2, HEIGHT).stroke({width: 2, color: AXES_COLOR});
      //axis arrow
      const triangleY = (WIDTH / 2 - arrowSize / 2) + ',' + (arrowSize) + ' ' +
        (WIDTH / 2 + arrowSize / 2) + ',' + (arrowSize) + ' ' +
        (WIDTH / 2) + ',' + (0);
      console.log('y arrow coordinates ' + triangleY)
      CANVAS.polygon(triangleY).fill(AXES_COLOR)
      CANVAS.text('y').font({
        size: 16,
        family: 'Menlo, sans-serif',
        anchor: 'end',
        fill: AXES_COLOR
      }).move(WIDTH / 2 - 1.5 * arrowSize, 1.7 * arrowSize)
    },

    drawScaleLabel(xStart, xStop, yStart, yStop, labelX, labelY, label) {
      CANVAS.line(convertX(xStart), convertY(yStart), convertX(xStop), convertY(yStop))
        .stroke({width: 2, color: AXES_COLOR});
      CANVAS.text(label).font({
        size: 16,
        family: 'Menlo, sans-serif',
        anchor: 'end',
        fill: AXES_COLOR
      }).move(convertX(labelX), convertY(labelY));
    },

    drawRValue() {
      CANVAS.text('R = ' + this.$props.currentR).font({
        size: 16,
        family: 'Menlo, sans-serif',
        anchor: 'end',
        fill: AXES_COLOR
      }).move(WIDTH - 50, HEIGHT - 50);
    },

    drawAxesScaleLabels() {
      console.log('Start drawing axes labels')
      const hatchLen = 0.1;
      const r = this.$props.currentR
      //x axis labels
      this.drawScaleLabel(-r, -r, hatchLen, -hatchLen, -r, -2 * hatchLen, "-R");
      this.drawScaleLabel(-r / 2, -r / 2, hatchLen, -hatchLen, -r / 2, -2 * hatchLen, "-R/2");
      this.drawScaleLabel(r / 2, r / 2, hatchLen, -hatchLen, r / 2, -2 * hatchLen, "R/2");
      this.drawScaleLabel(r, r, hatchLen, -hatchLen, r, -2 * hatchLen, "R");

      //y axis labels
      this.drawScaleLabel(hatchLen, -hatchLen, -r, -r, -4 * hatchLen, -r, "-R");
      this.drawScaleLabel(hatchLen, -hatchLen, -r / 2, -r / 2, -4 * hatchLen, -r / 2, "-R/2");
      this.drawScaleLabel(hatchLen, -hatchLen, r / 2, r / 2, -4 * hatchLen, r / 2, "R/2");
      this.drawScaleLabel(hatchLen, -hatchLen, r, r, -4 * hatchLen, r, "R");
    },

    drawCircle() {
      const r = this.$props.currentR
      CANVAS.circle(r / scale).fill(AREA_COLOR).move(convertX(-r / 2), convertY(r / 2))
      const fillUnusedCircle = (convertX(0)) + ',' + (convertY(0)) + ' ' +
        (convertX(-r)) + ',' + (convertY(0)) + ' ' +
        (convertX(-r)) + ',' + (convertY(r)) + ' ' +
        (convertX(r)) + ',' + (convertY(r)) + ' ' +
        (convertX(r)) + ',' + (convertY(-r)) + ' ' +
        (convertX(0)) + ',' + (convertY(-r));
      CANVAS.polygon(fillUnusedCircle).fill(BACKGROUND_COLOR)
    },

    drawRect() {
      const r = this.$props.currentR
      const area = (convertX(0)) + ',' + (convertY(0)) + ' ' +
        (convertX(-r)) + ',' + (convertY(0)) + ' ' +
        (convertX(-r)) + ',' + (convertY(r / 2)) + ' ' +
        (convertX(0)) + ',' + (convertY(r / 2));
      CANVAS.polygon(area).fill(AREA_COLOR)
    },

    drawTriangle() {
      const r = this.$props.currentR
      const area = (convertX(0)) + ',' + (convertY(0)) + ' ' +
        (convertX(0)) + ',' + (convertY(r)) + ' ' +
        (convertX(r)) + ',' + (convertY(0));
      CANVAS.polygon(area).fill(AREA_COLOR)
    },

    drawArea() {
      CANVAS.rect(WIDTH, HEIGHT).fill(BACKGROUND_COLOR);
      this.drawCircle();
      this.drawRect();
      this.drawTriangle();
    },

    drawPoint(x, y, result, pointScale) {
      let color = result ? '#0f0' : '#f00';
      CANVAS.circle(pointScale).fill(color).move(convertX(x) - pointScale / 2, convertY(y) - pointScale / 2);
    }
  }
}
</script>

