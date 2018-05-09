class BPMNElement {


	constructor(id, content) {

		content = `<g id='${id}'>${content}</g>`;
		d3.select(BPMNSettings.diagramSelector).html(d3.select(BPMNSettings.diagramSelector).html() + content);

		this.id = '#' + id;
		this.element = d3.select(this.id);

		this.rx = 0;
		this.ry = 0;

		this.vinculos = [];
	}

	wrap(text, width, x) {


		var
			words = text.text().split(/\s+/).reverse(),
			word,
			line = [],
			lineHeight = 1.1, // ems
			tspan = text.text(null).append("tspan").attr("x", x).attr("dy", 0 + "em");

		while (word = words.pop()) {
			line.push(word);
			tspan.text(line.join(" "));

			if (tspan.node().getComputedTextLength() > width) {
				line.pop();
				tspan.text(line.join(" "));
				line = [word];
				tspan = text.append("tspan").attr("x", x).attr("dy", lineHeight + "em").text(word);
			}
		}
	}


}