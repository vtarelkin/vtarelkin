class App extends React.Component {

    render() {
        return (
            <div className="main_container-content">
                <span className="sampleText">Hello world, what's your name? </span>
                <input type="text" className="inputBox" name="" placeholder="enter your name here" id="inputNameBox"/>
                <button onClick="add()">That's me!</button>
            </div>
        )
    }
}
//const domContainer = document.querySelector('#mainContainer');
ReactDOM.render(<App />, document.getElementById('main_container'));

