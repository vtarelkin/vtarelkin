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
            .then((responseJson) => {
                this.setState({receivedName: responseJson});

                console.log(this.state.name)
            })
            .catch((error) => {
                console.error(error);
            })
        ;
    }

    render() {
        const {name, receivedName} = this.state;
        return (
            <div className="main_container-content">
                <div className="greetingText">
                    {!!receivedName && <span className="greetingText" id="greetingTextBox">{receivedName}</span>}
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

