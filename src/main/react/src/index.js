import React from 'react'
import ReactDOM from 'react-dom'
import App from './App'
import reportWebVitals from './reportWebVitals'
import { StylesProvider, createGenerateClassName } from '@material-ui/styles'
import { MuiThemeProvider, createMuiTheme } from '@material-ui/core/styles'
import { CssBaseline } from '@material-ui/core'

const theme = createMuiTheme({
  palette: {
    primary: {
      light: '#b89f74',
      main: '#877148',
      dark: '#58461f',
      contrastText: '#fff',
    },
    secondary: {
      light: '#56a2ea',
      main: '#0074b7',
      dark: '#004987',
      contrastText: '#fff',
    },
  },
})

const generateClassName = createGenerateClassName({
  productionPrefix: 'emergencyNotificationsSoffit',
  disableGlobal: true,
})

ReactDOM.render(
  <React.StrictMode>
    <CssBaseline />
    <StylesProvider generateClassName={generateClassName}>
      <MuiThemeProvider theme={theme}>
        <App />
      </MuiThemeProvider>
    </StylesProvider>
  </React.StrictMode>,
  document.getElementById('emergency-notifications-soffit')
)

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals()
