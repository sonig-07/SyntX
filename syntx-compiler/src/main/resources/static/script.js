const samples = {
    java: `public class Main {
    public static void main(String[] args) {
        System.out.println("Hello from SyntX");
    }
}`,
    python: `print("Hello from SyntX")`,
    js: `console.log("Hello from SyntX");`
};

function setSample() {
    const lang = document.getElementById("language").value;
    document.getElementById("code").value = samples[lang];
}

async function runCode() {
    const language = document.getElementById("language").value;
    const code = document.getElementById("code").value;
    const output = document.getElementById("output");

    output.textContent = "Running...";

    try {
        const res = await fetch("/execute", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ language, code })
        });

        const data = await res.json();
        output.textContent = data.output || "No output";
    } catch {
        output.textContent = "❌ Backend not reachable";
    }
}

setSample();


const textarea = document.getElementById("code");
const lineNumbers = document.getElementById("lineNumbers");

function updateLineNumbers() {
    const lines = textarea.value.split("\n").length;
    lineNumbers.textContent = Array.from({ length: lines }, (_, i) => i + 1).join("\n");
}

textarea.addEventListener("input", updateLineNumbers);
textarea.addEventListener("scroll", () => {
    lineNumbers.scrollTop = textarea.scrollTop;
});

updateLineNumbers();
