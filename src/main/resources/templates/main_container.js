class App extends React.Component {
    state = {
        name: '',
        receivedName: ''
    }

    handleChange = (e) => {
        this.setState({
            name: e.target.value
        })
    }

    nameButtonClicked = (e) => {
        fetch('/hello', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body:
                JSON.stringify({name: this.state.name})
        })

            .then((response) => {
                return response.text();
            })

            .then((response) => {
                // Initialize the DOM parser
                var parser = new DOMParser();
                // // Parse the text
                var doc = parser.parseFromString(response, "text/html");
                var htmlString = new XMLSerializer().serializeToString(doc)
                console.log(doc.getElementsByTagName("div").item(0))
                this.setState({receivedName: htmlString});
            })
            .catch((error) => {
                console.error(error);
            })
        ;
    }

    async componentDidMount() {
        fetch(`/hello`, {method: 'GET'})
            .then(res => res.json())
            .then(json => this.setState({data: json}));
    }

    render() {
        const {name, receivedName} = this.state;
        return (
            <div className="main_container-content">
                <div className="greetingText">
                    <div dangerouslySetInnerHTML={{__html: receivedName}}></div>
                    {/*<div dangerouslySetInnerHTML={receivedName}/>*/}
                    {/*{!!receivedName && <span className="greetingText" id="greetingTextBox">{receivedName}</span>}*/}
                </div>
                <div>
                    <span className="sampleText">Hello world, what's your name? </span>
                    <input type="text" className="inputBox" name="" placeholder="enter your name here"
                           id="inputNameBox" onChange={this.handleChange}/>
                    <button disabled={!name} onClick={this.nameButtonClicked}>That's me!</button>
                </div>
            </div>
        )
    }
}

ReactDOM.render(<App/>, document.getElementById('main_container'));

