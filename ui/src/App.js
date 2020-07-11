import React from 'react';
import Layout from "./hoc/Layout/Layout";
import UserProfile from "./containers/UserProfile/UserProfile";

function App() {
    return (
        <Layout>
            <UserProfile />
        </Layout>
    );
}

export default App;
